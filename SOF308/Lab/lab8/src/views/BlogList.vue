<template>
  <section class="page blog-list">
    <h2 class="section-title">Blog Posts</h2>
    <div class="posts-grid">
      <div v-for="post in posts" :key="post.id" class="post-card">
        <h3 class="post-title">
          <router-link :to="{ name: 'BlogPost', params: { id: post.id } }">
            {{ post.title }}
          </router-link>
        </h3>
        <div class="meta">
          <span>By {{ post.author }}</span>
          <span> • {{ post.date }}</span>
        </div>
        <p class="excerpt">{{ post.excerpt }}</p>
        <router-link
          :to="{ name: 'BlogPost', params: { id: post.id } }"
          class="read-more"
        >
          Read more →
        </router-link>
      </div>
    </div>
  </section>
</template>

<script>
export default {
  name: "BlogListPage",
  data() {
    return {
      posts: [
        {
          id: 1,
          title: "🥗 Lợi ích của ăn rau xanh hàng ngày",
          author: "Lan Hoàng",
          date: "2025-07-20",
          excerpt:
            "Rau xanh cung cấp vitamin, khoáng chất và chất xơ. Giúp tăng sức đề kháng và cải thiện tiêu hóa.",
        },
        {
          id: 2,
          title: "🍎 Trái cây - Nguồn vitamin tự nhiên",
          author: "Minh Châu",
          date: "2025-07-22",
          excerpt:
            "Trái cây giàu vitamin C, chất chống oxy hóa. Nên ăn tươi thay vì uống nước ép để có đủ chất xơ.",
        },
        {
          id: 3,
          title: "🐟 Protein từ cá - Thực phẩm vàng cho não bộ",
          author: "Phương Mai",
          date: "2025-07-25",
          excerpt:
            "Cá giàu omega-3, protein chất lượng cao. Đặc biệt tốt cho phát triển trí não và bảo vệ tim mạch.",
        },
        {
          id: 4,
          title: "🥛 Sữa và sản phẩm từ sữa cho xương chắc khỏe",
          author: "Đức Anh",
          date: "2025-07-27",
          excerpt:
            "Sữa cung cấp canxi, protein và vitamin D cần thiết cho xương và răng chắc khỏe.",
        },
        {
          id: 5,
          title: "� Ngũ cốc nguyên hạt - Năng lượng bền vững",
          author: "Thu Trang",
          date: "2025-07-28",
          excerpt:
            "Ngũ cốc nguyên hạt cung cấp carbohydrate phức hợp, chất xơ. Giúp duy trì năng lượng ổn định.",
        },
        {
          id: 6,
          title: "🥜 Hạt và đậu - Protein thực vật tuyệt vời",
          author: "Quang Huy",
          date: "2025-07-30",
          excerpt:
            "Hạt và đậu là nguồn protein thực vật, chất béo tốt. Đặc biệt phù hợp với người ăn chay.",
        },
        {
          id: 7,
          title: "� Uống đủ nước - Chìa khóa sức khỏe",
          author: "Thanh Hương",
          date: "2025-08-01",
          excerpt:
            "Nước chiếm 60-70% cơ thể. Uống đủ nước giúp da đẹp, thận khỏe và tinh thần tỉnh táo.",
        },
        {
          id: 8,
          title: "🍯 Thực phẩm tự nhiên thay thế đường",
          author: "Kim Oanh",
          date: "2025-08-03",
          excerpt:
            "Mật ong, nước cốt dừa, trái cây là lựa chọn ngọt tự nhiên và lành mạnh hơn đường tinh luyện.",
        },
      ],
    };
  },
};
</script>

<style scoped>
.section-title {
  font-size: 1.5rem;
  margin-bottom: 1rem;
  color: #495057;
  font-weight: 600;
}

.posts-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 1rem;
}

.post-card {
  background: white;
  border: 1px solid #e9ecef;
  border-radius: 6px;
  padding: 1rem;
  transition: box-shadow 0.2s;
}

.post-card:hover {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

.post-title {
  margin: 0 0 0.5rem 0;
  font-size: 1.1rem;
}

.post-title a {
  text-decoration: none;
  color: #495057;
}

.post-title a:hover {
  color: #007bff;
}

.meta {
  font-size: 0.8rem;
  color: #6c757d;
  margin-bottom: 0.75rem;
}

.excerpt {
  color: #495057;
  font-size: 0.9rem;
  margin-bottom: 1rem;
  line-height: 1.5;
}

.read-more {
  font-size: 0.85rem;
  color: #007bff;
  text-decoration: none;
  font-weight: 500;
}

.read-more:hover {
  text-decoration: underline;
}
</style>
