import { ref, computed } from 'vue'
import { dataService, type User } from './dataService'

class AuthService {
  private currentUser = ref<User | null>(null)

  constructor() {
    this.loadSession()
  }

  private loadSession() {
    const savedUser = localStorage.getItem('currentUser')
    if (savedUser) {
      this.currentUser.value = JSON.parse(savedUser)
    }
  }

  private saveSession() {
    if (this.currentUser.value) {
      localStorage.setItem('currentUser', JSON.stringify(this.currentUser.value))
    } else {
      localStorage.removeItem('currentUser')
    }
  }

  get user() {
    return computed(() => this.currentUser.value)
  }

  get isAuthenticated() {
    return computed(() => !!this.currentUser.value)
  }

  async login(email: string, password: string): Promise<{ success: boolean; message: string }> {
    const user = dataService.getUserByEmail(email)

    if (!user) {
      return { success: false, message: 'Email không tồn tại' }
    }

    if (user.password !== password) {
      return { success: false, message: 'Mật khẩu không đúng' }
    }

    this.currentUser.value = user
    this.saveSession()
    return { success: true, message: 'Đăng nhập thành công' }
  }

  async register(email: string, username: string, password: string): Promise<{ success: boolean; message: string }> {
    if (dataService.getUserByEmail(email)) {
      return { success: false, message: 'Email đã tồn tại' }
    }

    const users = dataService.getUsers()
    if (users.find(user => user.username === username)) {
      return { success: false, message: 'Tên người dùng đã tồn tại' }
    }

    try {
      const newUser = await dataService.createUser({
        email,
        username,
        password,
        avatar: ''
      })

      this.currentUser.value = newUser
      this.saveSession()
      return { success: true, message: 'Đăng ký thành công' }
    } catch (error) {
      return { success: false, message: 'Có lỗi xảy ra khi đăng ký' }
    }
  }

  logout() {
    this.currentUser.value = null
    this.saveSession()
  }

  async updateProfile(userData: Partial<User>): Promise<{ success: boolean; message: string }> {
    if (!this.currentUser.value) {
      return { success: false, message: 'Bạn chưa đăng nhập' }
    }

    try {
      const updatedUser = await dataService.updateUser(this.currentUser.value.id, userData)
      if (updatedUser) {
        this.currentUser.value = updatedUser
        this.saveSession()
        return { success: true, message: 'Cập nhật thông tin thành công' }
      }
      return { success: false, message: 'Không thể cập nhật thông tin' }
    } catch (error) {
      return { success: false, message: 'Có lỗi xảy ra khi cập nhật' }
    }
  }
}

export const authService = new AuthService()
