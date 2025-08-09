<template>
  <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
      <div class="container-fluid">
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
              <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="collapsibleNavbar">
              <ul class="navbar-nav">
                  <li class="nav-item"><router-link class="nav-link" :to="{name:'home'}">Trang chủ</router-link></li>
                  <li class="nav-item"><router-link class="nav-link" :to="{name:'about'}">Giới thiệu</router-link></li>
                  <li class="nav-item"><router-link class="nav-link" :to="{name:'contact'}">Liên hệ</router-link></li>
              </ul>
          </div>
      </div>
  </nav>
</template>

<script setup>

</script>

<style scoped>
:global(body) {
  padding-top: 120px;
}
</style>
