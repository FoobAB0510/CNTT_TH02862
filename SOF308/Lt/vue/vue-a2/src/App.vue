<script setup>
import HelloWorld from './components/HelloWorld.vue';
import TopMenu from './components/TopMenu.vue';
import Interpolation from './components/Interpolation.vue';
import RawHTML from './components/RawHTML.vue';
import Attribute from './components/Attribute.vue';
import JavaScriptExp from './components/JavaScriptExp.vue';
import Vbind from './components/Vbind.vue';
import VOn from './components/VOn.vue';
import VOnCallMethod from './components/VOnCallMethod.vue';
import BoostrapButton from './components/BoostrapButton.vue';
</script>

<template>
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
</style>
