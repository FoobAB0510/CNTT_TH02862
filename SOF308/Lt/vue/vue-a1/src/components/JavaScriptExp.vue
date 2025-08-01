<template>
    <p>Phương thức sayHi() trả về {{ sayHi() }}</p>
    <p>Số này là số {{ number%2 == 0? 'Chẵn' : 'Lẻ' }}</p>
</template>

<script setup>
    const number = 22;
    const sayHi = () => {
        return 'vuejs.org';
    };
</script>

