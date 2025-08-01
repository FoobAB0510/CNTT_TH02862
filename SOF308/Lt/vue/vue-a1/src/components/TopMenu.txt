<template>
    <nav class="menu">
        <ul>
            <li><a href="#">Link 1</a></li>
            <li><a href="#">Link 2</a></li>
            <li><a href="#">Link 3</a></li>
            <li><a href="#">Link 4</a></li>
            <li><a href="#">Link 5</a></li>
        </ul>
    </nav>
</template>
<script>
</script>
<style scoped>
.menu {
    background-color: transparent;
    padding: 0.5rem;
}
.menu ul {
    list-style: none;
    display: flex;
    gap: 0.75rem;
    margin: 0;
    padding: 0;
}
.menu li a{
    color: #0067B8;
    padding: 0.5rem 1rem;
    display: block;
    font-size: 1rem;
    font-weight: 300;
}
.menu li a:hover {
    background-color: transparent;
    text-decoration: underline;
}
</style>