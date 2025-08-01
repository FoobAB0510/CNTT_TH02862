<script>
import { reactive } from 'vue';

export default {
  setup() {
    const stfu = reactive({
      name: 'stfu',
      age: 18,
      address: '123 Main St'
    });

    const increment = () => {
      stfu.age++;
    };

    return { stfu };
  }
};
</script>
