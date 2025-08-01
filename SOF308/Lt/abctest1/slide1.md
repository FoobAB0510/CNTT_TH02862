## 📦 **Phần I: Giới thiệu Bootstrap**

### 1. **Bootstrap là gì?**

* Framework front-end miễn phí
* Dễ học, dễ dùng, responsive ngon lành
* Có sẵn HTML, CSS, JavaScript templates
* Từ bản 5 trở lên đã bỏ jQuery, chỉ xài JS thuần

### 2. **Cách chèn Bootstrap vào web**

Có 2 cách:

* **Tải về** từ [getbootstrap.com](https://getbootstrap.com/docs/5.3/getting-started/download/)
* **Xài trực tiếp** bằng CDN (khỏi cần tải)

```html
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
```

---

## 🧱 **Hệ thống lưới (Grid System)**

### 1. **Container vs Container-fluid**

* `.container`: khung giới hạn chiều ngang
* `.container-fluid`: kéo dài full chiều ngang trình duyệt

### 2. **Grid 12 cột**

* Dựa trên Flexbox
* Responsive tùy màn hình
* Các lớp: `.col-`, `.col-sm-`, `.col-md-`, `.col-lg-`, `.col-xl-`, `.col-xxl-`
```
Class	Áp dụng cho kích thước màn hình	Width tối thiểu
.col-	Mọi kích thước luôn luôn áp dụng	0px
.col-sm-	Thiết bị nhỏ (small)	≥ 576px
.col-md-	Thiết bị vừa (medium)	≥ 768px
.col-lg-	Thiết bị lớn (large)	≥ 992px
.col-xl-	Thiết bị rất lớn (extra large)	≥ 1200px
.col-xxl-	Thiết bị siêu to khổng lồ (extra x2)	≥ 1400px
```

### 3. **Cách chia lưới**

```html
<div class="row">
  <div class="col-sm-4">1</div>
  <div class="col-sm-4">2</div>
  <div class="col-sm-4">3</div>
</div>
```

---

## 🧩 **Phần II: Các thành phần cơ bản (Components)**

### 1. **Navigation Bar**

* Thanh menu dễ làm
* Có thể đổi màu (`bg-warning`, `bg-dark`, etc.)
* Hỗ trợ responsive và sticky top

### 2. **Icon**

* Không còn Glyphicons
* Dùng **Font Awesome**:

```html
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
```

### 3. **Card**

* Khung hiển thị nội dung đẹp
* Có thể thêm `card-header`, `card-footer`, `img`, `button`...

### 4. **List Group**

* Hiển thị danh sách đẹp mắt
* Có thể thêm badge, số thứ tự, hoặc làm list nằm ngang
