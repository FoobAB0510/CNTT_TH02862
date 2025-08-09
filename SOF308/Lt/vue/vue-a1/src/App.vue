<script setup>
import { ref } from 'vue';
import conditionalrendering from './components/conditionalrendering.vue';
import ListRender from './components/listRender.vue';
import listrendering from './components/listrendering.vue'
import eventHandle from './components/eventHandle.vue';
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
    <div>
      <ListRender></ListRender>
    </div>

    <div>
      <listrendering></listrendering>
    </div>
    <div>
      <eventHandle></eventHandle>
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
