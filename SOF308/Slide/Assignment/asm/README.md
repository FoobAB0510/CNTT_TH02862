# ASM - Vue 3 Blog Application

Ứng dụng Blog được xây dựng với Vue 3, TypeScript và JSON Server.

## 🚀 Tính năng chính

- ✅ **Quản lý người dùng** - Đăng ký, đăng nhập, cập nhật profile
- ✅ **Quản lý bài viết** - Tạo, xem, sửa, xóa bài viết
- ✅ **Hệ thống bình luận** - Thêm, xóa bình luận
- ✅ **JSON Server API** - RESTful API với persistent data
- ✅ **Responsive UI** - Bootstrap 5 + custom CSS

## 🛠️ Công nghệ sử dụng

- **Frontend**: Vue 3 + TypeScript + Vite
- **Backend**: JSON Server
- **UI Framework**: Bootstrap 5
- **Icons**: Bootstrap Icons

## 📦 Cài đặt

```sh
npm install
```

## 🚀 Khởi chạy dự án

### Phương pháp 1: Chạy cả server và client (khuyến nghị)

```sh
npm run dev:full
```

### Phương pháp 2: Chạy từng phần

```sh
# Terminal 1: Khởi động JSON Server
npm run server

# Terminal 2: Khởi động Vue Development Server
npm run dev
```

### Phương pháp 3: Sử dụng batch file (Windows)

```sh
# Khởi động JSON Server
./start-server.bat

# Trong terminal khác, khởi động Vue app
npm run dev
```

## 🌐 Truy cập ứng dụng

- **Vue App**: http://localhost:5173
- **JSON Server API**: http://localhost:3000

## 📚 API Endpoints

- `GET /users` - Danh sách người dùng
- `GET /posts` - Danh sách bài viết
- `POST /users` - Tạo người dùng mới
- `POST /posts` - Tạo bài viết mới
- `PUT/PATCH /posts/:id` - Cập nhật bài viết
- `DELETE /posts/:id` - Xóa bài viết

## 🔧 Scripts có sẵn

```sh
npm run dev
```

### Type-Check, Compile and Minify for Production

```sh
npm run build
```

### Lint with [ESLint](https://eslint.org/)

```sh
npm run lint
```
