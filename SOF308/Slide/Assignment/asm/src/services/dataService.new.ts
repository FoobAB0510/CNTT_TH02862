export interface User {
  id: number
  email: string
  username: string
  password: string
  avatar: string
}

export interface Comment {
  id: number
  content: string
  authorName: string
  createdAt: string
}

export interface Post {
  id: number
  title: string
  content: string
  authorId: number
  authorName: string
  createdAt: string
  image: string
  comments: Comment[]
}

class DataService {
  private readonly API_URL = 'http://localhost:3000'
  private users: User[] = []
  private posts: Post[] = []

  constructor() {
    this.loadData()
  }

  private async loadData() {
    try {

      await this.loadUsers()

      await this.loadPosts()
      console.log('✅ Data loaded from json-server successfully!')
    } catch (error) {
      console.error('❌ Error loading data from json-server:', error)
    }
  }

  private async loadUsers() {
    try {
      const response = await fetch(`${this.API_URL}/users`)
      if (response.ok) {
        this.users = await response.json()
        console.log('📖 Loaded users from json-server:', this.users.length)
      }
    } catch (error) {
      console.error('Error loading users:', error)
    }
  }

  private async loadPosts() {
    try {
      const response = await fetch(`${this.API_URL}/posts`)
      if (response.ok) {
        this.posts = await response.json()
        console.log('📖 Loaded posts from json-server:', this.posts.length)
      }
    } catch (error) {
      console.error('Error loading posts:', error)
    }
  }

  private downloadDatabaseFile(data: any) {
    const blob = new Blob([JSON.stringify(data, null, 2)], {
      type: 'application/json'
    })

    const url = URL.createObjectURL(blob)
    const a = document.createElement('a')
    a.href = url
    a.download = `database-backup-${Date.now()}.json`
    a.click()
    URL.revokeObjectURL(url)

    console.log('📁 Database backup downloaded!')
  }


  async exportDatabase() {
    try {
      const [usersResponse, postsResponse] = await Promise.all([
        fetch(`${this.API_URL}/users`),
        fetch(`${this.API_URL}/posts`)
      ])

      if (usersResponse.ok && postsResponse.ok) {
        const users = await usersResponse.json()
        const posts = await postsResponse.json()

        const data = { users, posts }
        this.downloadDatabaseFile(data)
      }
    } catch (error) {
      console.error('❌ Error exporting database:', error)
    }
  }


  async importDatabase(jsonData: any): Promise<boolean> {
    try {
      if (jsonData.posts && Array.isArray(jsonData.posts)) {

        const existingPosts = await fetch(`${this.API_URL}/posts`).then(r => r.json())
        for (const post of existingPosts) {
          await fetch(`${this.API_URL}/posts/${post.id}`, { method: 'DELETE' })
        }


        for (const post of jsonData.posts) {
          await fetch(`${this.API_URL}/posts`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(post)
          })
        }

        await this.loadPosts()
        console.log('✅ Database imported successfully!')
        return true
      } else {
        console.error('❌ Invalid database format')
        return false
      }
    } catch (error) {
      console.error('❌ Error importing database:', error)
      return false
    }
  }


  async resetToOriginalDatabase() {
    try {

      const existingPosts = await fetch(`${this.API_URL}/posts`).then(r => r.json())
      for (const post of existingPosts) {
        await fetch(`${this.API_URL}/posts/${post.id}`, { method: 'DELETE' })
      }


      await this.loadData()
      console.log('🔄 Reset to original database')
    } catch (error) {
      console.error('❌ Error resetting database:', error)
    }
  }


  getUsers(): User[] {
    return this.users
  }

  getUserById(id: number): User | undefined {
    return this.users.find(user => user.id === id)
  }

  getUserByEmail(email: string): User | undefined {
    return this.users.find(user => user.email === email)
  }

  async createUser(userData: Omit<User, 'id'>): Promise<User> {
    try {
      const response = await fetch(`${this.API_URL}/users`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userData)
      })

      if (response.ok) {
        const newUser = await response.json()
        this.users.push(newUser)
        console.log('✅ User created on json-server')
        return newUser
      } else {
        throw new Error('Failed to create user')
      }
    } catch (error) {
      console.error('❌ Error creating user:', error)
      throw error
    }
  }

  async updateUser(id: number, userData: Partial<User>): Promise<User | null> {
    try {
      const response = await fetch(`${this.API_URL}/users/${id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(userData)
      })

      if (response.ok) {
        const updatedUser = await response.json()
        const userIndex = this.users.findIndex(user => user.id === id)
        if (userIndex !== -1) {
          this.users[userIndex] = updatedUser
        }
        console.log('✅ User updated on json-server')
        return updatedUser
      } else {
        throw new Error('Failed to update user')
      }
    } catch (error) {
      console.error('❌ Error updating user:', error)
      return null
    }
  }


  getPosts(): Post[] {
    return this.posts.sort((a, b) => new Date(b.createdAt).getTime() - new Date(a.createdAt).getTime())
  }

  getPostById(id: number): Post | undefined {
    return this.posts.find(post => post.id === id)
  }

  async createPost(postData: Omit<Post, 'id' | 'comments' | 'createdAt'>): Promise<Post> {
    try {
      const newPost = {
        ...postData,
        comments: [],
        createdAt: new Date().toISOString()
      }

      const response = await fetch(`${this.API_URL}/posts`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newPost)
      })

      if (response.ok) {
        const createdPost = await response.json()
        this.posts.push(createdPost)
        console.log('✅ Post created on json-server')
        return createdPost
      } else {
        throw new Error('Failed to create post')
      }
    } catch (error) {
      console.error('❌ Error creating post:', error)
      throw error
    }
  }

  async updatePost(id: number, postData: Partial<Post>): Promise<Post | null> {
    try {
      const response = await fetch(`${this.API_URL}/posts/${id}`, {
        method: 'PATCH',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(postData)
      })

      if (response.ok) {
        const updatedPost = await response.json()
        const postIndex = this.posts.findIndex(post => post.id === id)
        if (postIndex !== -1) {
          this.posts[postIndex] = updatedPost
        }
        return updatedPost
      } else {
        throw new Error('Failed to update post')
      }
    } catch (error) {
      return null
    }
  }

  async deletePost(id: number): Promise<boolean> {
    try {
      const response = await fetch(`${this.API_URL}/posts/${id}`, {
        method: 'DELETE'
      })

      if (response.ok) {
        const postIndex = this.posts.findIndex(post => post.id === id)
        if (postIndex !== -1) {
          this.posts.splice(postIndex, 1)
        }
        return true
      } else {
        throw new Error('Failed to delete post')
      }
    } catch (error) {
      return false
    }
  }


  async addComment(postId: number, commentData: Omit<Comment, 'id' | 'createdAt'>): Promise<Comment | null> {
    try {
      const post = this.getPostById(postId)
      if (!post) return null

      const newComment: Comment = {
        id: Math.max(...post.comments.map(c => c.id), 0) + 1,
        ...commentData,
        createdAt: new Date().toISOString()
      }


      const updatedPost = {
        ...post,
        comments: [...post.comments, newComment]
      }

      const response = await fetch(`${this.API_URL}/posts/${postId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedPost)
      })

      if (response.ok) {
        const result = await response.json()
        const postIndex = this.posts.findIndex(p => p.id === postId)
        if (postIndex !== -1) {
          this.posts[postIndex] = result
        }
        return newComment
      } else {
        throw new Error('Failed to add comment')
      }
    } catch (error) {
      return null
    }
  }

  async deleteComment(postId: number, commentId: number): Promise<boolean> {
    try {
      const post = this.getPostById(postId)
      if (!post) return false

      const commentIndex = post.comments.findIndex(comment => comment.id === commentId)
      if (commentIndex === -1) return false


      const updatedComments = post.comments.filter(c => c.id !== commentId)
      const updatedPost = {
        ...post,
        comments: updatedComments
      }

      const response = await fetch(`${this.API_URL}/posts/${postId}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(updatedPost)
      })

      if (response.ok) {
        const result = await response.json()
        const postIndex = this.posts.findIndex(p => p.id === postId)
        if (postIndex !== -1) {
          this.posts[postIndex] = result
        }
        return true
      } else {
        throw new Error('Failed to delete comment')
      }
    } catch (error) {
      return false
    }
  }
}

export const dataService = new DataService()
