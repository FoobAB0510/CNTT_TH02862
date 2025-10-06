<template>
  <section class="page blog-post">
    <div class="post-wrapper">
      <div v-if="post" class="post-card-full">
        <div class="header">
          <h1 class="title">{{ post.title }}</h1>
          <div class="meta">
            <span>Bởi {{ post.author }}</span>
            <span>• {{ post.date }}</span>
          </div>
        </div>
        <div class="content" v-html="post.contentHtml"></div>
        <div class="back">
          <router-link :to="{ name: 'BlogList' }"
            >← Quay lại tất cả bài viết</router-link
          >
        </div>
      </div>
      <div v-else class="not-found">
        <h2>Không tìm thấy bài viết</h2>
        <p>Không thể tìm thấy bài viết với ID: {{ $route.params.id }}</p>
        <router-link :to="{ name: 'BlogList' }"
          >Quay lại danh sách blog</router-link
        >
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "TrangBaiVietBlog",
  data() {
    return {
      posts: [
        {
          id: 1,
          title: "🥗 Lợi ích của ăn rau xanh hàng ngày",
          author: "Lan Hoàng",
          date: "2025-07-20",
          contentHtml: `
            <p>Rau xanh là nguồn cung cấp <strong>vitamin</strong>, <strong>khoáng chất</strong> và <strong>chất xơ</strong> tuyệt vời cho cơ thể. Việc bổ sung rau xanh vào bữa ăn hàng ngày giúp tăng cường sức đề kháng và cải thiện tiêu hóa.</p>
            <h3>Các loại rau nên ăn</h3>
            <p>Rau cải, rau muống, cải thảo, súp lơ xanh đều rất tốt cho sức khỏe. Nên ăn ít nhất 300g rau xanh mỗi ngày.</p>
            <h3>Cách chế biến</h3>
            <p>Nên luộc nhẹ hoặc xào nhanh để giữ được tối đa dưỡng chất trong rau.</p>
          `,
        },
        {
          id: 2,
          title: "🍎 Trái cây - Nguồn vitamin tự nhiên",
          author: "Minh Châu",
          date: "2025-07-22",
          contentHtml: `
            <p>Trái cây cung cấp <strong>vitamin C</strong>, <strong>chất chống oxy hóa</strong> và <strong>đường tự nhiên</strong> giúp cơ thể khỏe mạnh. Mỗi loại trái cây có những lợi ích riêng biệt.</p>
            <h3>Trái cây theo mùa</h3>
            <p>Cam, quýt (mùa đông), xoài, nhãn (mùa hè), táo, lê (quanh năm) đều rất bổ dưỡng.</p>
            <p>Nên ăn trái cây tươi thay vì uống nước ép để có đủ chất xơ.</p>
          `,
        },
        {
          id: 3,
          title: "🐟 Protein từ cá - Thực phẩm vàng cho não bộ",
          author: "Phương Mai",
          date: "2025-07-25",
          contentHtml: `
            <p>Cá giàu <strong>omega-3</strong>, <strong>protein</strong> chất lượng cao và ít cholesterol xấu. Đặc biệt tốt cho phát triển trí não và bảo vệ tim mạch.</p>
            <h3>Các loại cá nên ăn</h3>
            <p>Cá hồi, cá thu, cá mackerel giàu omega-3. Cá rô phi, cá tra cũng là nguồn protein tốt và giá cả phải chăng.</p>
            <p>Nên ăn cá 2-3 lần/tuần để có sức khỏe tốt nhất.</p>
          `,
        },
        {
          id: 4,
          title: "🥛 Sữa và sản phẩm từ sữa cho xương chắc khỏe",
          author: "Đức Anh",
          date: "2025-07-27",
          contentHtml: `
            <p>Sữa cung cấp <strong>canxi</strong>, <strong>protein</strong> và <strong>vitamin D</strong> cần thiết cho xương và răng chắc khỏe. Đặc biệt quan trọng với trẻ em và người cao tuổi.</p>
            <h3>Sản phẩm từ sữa</h3>
            <p>Yaourt, phô mai, sữa chua đều là nguồn canxi tuyệt vời. Men vi sinh trong sữa chua còn tốt cho đường ruột.</p>
          `,
        },
        {
          id: 5,
          title: "� Ngũ cốc nguyên hạt - Năng lượng bền vững",
          author: "Thu Trang",
          date: "2025-07-28",
          contentHtml: `
            <p>Ngũ cốc nguyên hạt cung cấp <strong>carbohydrate phức hợp</strong>, <strong>chất xơ</strong> và <strong>vitamin B</strong>. Giúp duy trì năng lượng ổn định và no lâu hơn.</p>
            <h3>Lựa chọn thông minh</h3>
            <p>Gạo lứt, yến mạch, quinoa, bánh mì nguyên cám tốt hơn nhiều so với tinh bột trắng đã qua chế biến.</p>
          `,
        },
        {
          id: 6,
          title: "🥜 Hạt và đậu - Protein thực vật tuyệt vời",
          author: "Quang Huy",
          date: "2025-07-30",
          contentHtml: `
            <p>Các loại hạt và đậu là nguồn <strong>protein thực vật</strong>, <strong>chất béo tốt</strong> và <strong>khoáng chất</strong> quan trọng. Đặc biệt phù hợp với người ăn chay.</p>
            <h3>Các loại nên ăn</h3>
            <p>Hạnh nhân, óc chó, đậu đen, đậu xanh, đậu phụ đều rất bổ dưỡng. Nên ăn một nắm hạt mỗi ngày.</p>
          `,
        },
        {
          id: 7,
          title: "� Uống đủ nước - Chìa khóa sức khỏe",
          author: "Thanh Hương",
          date: "2025-08-01",
          contentHtml: `
            <p>Nước chiếm 60-70% cơ thể người, tham gia vào mọi quá trình trao đổi chất. <strong>Uống đủ nước</strong> giúp da đẹp, thận khỏe và tinh thần tỉnh táo.</p>
            <h3>Bao nhiêu là đủ?</h3>
            <p>Người trưởng thành nên uống 2-2.5 lít nước mỗi ngày. Tăng lượng nước khi trời nóng hoặc vận động nhiều.</p>
            <p>Nước lọc, trà xanh nhạt, nước dừa đều là lựa chọn tốt.</p>
          `,
        },
        {
          id: 8,
          title: "🍯 Thực phẩm tự nhiên thay thế đường",
          author: "Kim Oanh",
          date: "2025-08-03",
          contentHtml: `
            <p>Đường tinh luyện có thể gây béo phì và các vấn đề sức khỏe. <strong>Mật ong</strong>, <strong>nước cốt dừa</strong>, <strong>trái cây</strong> là những lựa chọn ngọt tự nhiên và lành mạnh hơn.</p>
            <h3>Lợi ích</h3>
            <p>Mật ong có chất chống oxy hóa, trái cây có vitamin, stevia không có calo. Giúp thỏa mãn cơn thèm ngọt mà vẫn bảo vệ sức khỏe.</p>
          `,
        },
      ],
    };
  },
  computed: {
    post() {
      const id = parseInt(this.$route.params.id, 10);
      return this.posts.find((p) => p.id === id) || null;
    },
  },
};
</script>

<style scoped>
.post-wrapper {
  max-width: 800px;
  margin: 0 auto;
}

.post-card-full {
  background: white;
  border: 1px solid #e9ecef;
  border-radius: 6px;
  padding: 1.5rem;
}

.header {
  margin-bottom: 1rem;
  border-bottom: 1px solid #e9ecef;
  padding-bottom: 1rem;
}

.title {
  margin: 0 0 0.5rem 0;
  font-size: 1.8rem;
  color: #495057;
  font-weight: 600;
}

.meta {
  font-size: 0.9rem;
  color: #6c757d;
}

.content {
  line-height: 1.6;
  margin: 1rem 0;
  color: #495057;
}

.content h3 {
  margin-top: 1.5rem;
  margin-bottom: 0.5rem;
  color: #495057;
  font-size: 1.2rem;
}

.content p {
  margin-bottom: 1rem;
}

.content code {
  background: #f8f9fa;
  padding: 0.2rem 0.4rem;
  border-radius: 3px;
  font-size: 0.9rem;
}

.back {
  margin-top: 1.5rem;
  padding-top: 1rem;
  border-top: 1px solid #e9ecef;
}

.back a {
  text-decoration: none;
  color: #007bff;
  font-size: 0.9rem;
}

.back a:hover {
  text-decoration: underline;
}

.not-found {
  background: #f8d7da;
  color: #721c24;
  padding: 1rem;
  border-radius: 6px;
  border: 1px solid #f5c6cb;
  text-align: center;
}

.not-found h2 {
  margin-bottom: 0.5rem;
  font-size: 1.3rem;
}

.not-found a {
  color: #007bff;
  text-decoration: none;
}

.not-found a:hover {
  text-decoration: underline;
}
</style>
