<template>
    <h2 v-bind:class="className" v-bind:style="styleData" v-html="content"></h2>
</template>

<script setup>
    const content = 'Sup my NI';
    const className = 'text-red';
    const styleData = 'font-size: 2.5rem'; 
</script>

<style scoped>
    .text-red {
        color: red;
    }
</style>