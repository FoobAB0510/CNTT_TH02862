<script setup>
// Import hàm ref từ Vue để tạo biến phản ứng (reactive)
import { ref } from 'vue';
// Khai báo biển phản ứng count với giá trị = 0
    const conut = ref(0);
// Định nghĩa phương thức tăng giá trị của biến count
    const increment = () => {
        conut.value++;
    };
// Định nghĩa phương thức reset để cài đặt lại giá trị của biến count về 0
    const reset = () => {
        conut.value = 0;
    };
</script>