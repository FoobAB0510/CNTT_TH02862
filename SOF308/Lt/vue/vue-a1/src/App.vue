<script setup>
import { ref } from 'vue';
import BoostrapButton from './components/BoostrapButton.vue';
import TopMenu from './components/TopMenu.vue';
import conditionalrendering from './components/conditionalrendering.vue';

defineProps({
  msg: {
    type: Object,
    default: () => ({ text: 'Welcome to Your Vue.js App' })
  }
});

const stfu = ref({
  name: 'stfu',
  age: 18,
  address: '123 Main St'
})
</script>

<!-- <template>
  <div class="app">
    <TopMenu/>
    <main>
      <BoostrapButton/>
    </main>
  </div>
</template>

<style scoped>
.app{
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: stretch;
}
.main{
  padding: 2rem;
}
</style> -->


<template>
  <div class="app">
    <div>
      <conditionalrendering></conditionalrendering>
    </div>
  </div>
</template>

<style scoped>
.app{
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: stretch;
}
.main{
  padding: 2rem;
}
</style>
