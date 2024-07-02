<template>
  <aside class="nav-container" :class="{playerActive: currentAudio}">
    <div class="nav-bar">
      <router-link class="nav-item" to="/">
        <span class="material-icons">explore</span>
        Explore
      </router-link>
      <router-link class="nav-item" v-if="!isLoggedIn" to="/login">
        <span class="material-icons">account_circle</span>
        My profile
      </router-link>
      <router-link class="nav-item" v-if="isLoggedIn" :to="{ name: 'profile', params: { id: currentUserId }}">
        <span class="material-icons">account_circle</span>
        My profile
      </router-link>
      <div v-if="isLoggedIn" class="nav-item bottom"  @click="logout">
        <span class="material-icons">logout</span>
        Logout
      </div>
      <router-link v-if="!isLoggedIn" class="nav-item bottom" to="/login">
        <span class="material-icons">login</span>
        Login
      </router-link>
    </div>
  </aside>
</template>

<script>
export default {
  data() {
    return {
      currentUserId: localStorage.getItem('currentUserId')
    }
  },
  computed: {
    isLoggedIn() {
      return this.$store.getters.isLoggedIn;
    },
    currentAudio() {
      return this.$store.state.currentAudio;
    },
  },
  created() {
    this.$store.dispatch('fetchCurrentUser');
  },
  name: "Nav-bar",
  methods: {
    async logout() {
      await this.$store.dispatch('logout');
      // Redirect to the login page or any other desired route after logout
      this.$router.push('/login');
    },
  },
}
</script>

<style lang="scss" scoped>
@import '../assets/styles/global.scss';

.nav-container {
  z-index: 99;
  display: flex;
  width: fit-content;
  justify-content: center;
  padding: 20px;
  height: 100vh;
  float: left;
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  transition: .2s;

  &.playerActive{
    padding-bottom: 106px;
  }

  .nav-bar {
    background: $background-2;
    display: flex;
    flex-direction: column;
    padding: 10px;
    height: 100%;
    gap: 10px;
    border-radius: 18px;

    .nav-item {
      padding: 10px;
      height: 80px;
      width: 80px;
      border-radius: 8px;
      background: $background-3;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      text-decoration: none;
      gap: 5px;
      position: relative;
      font-size: .8em;
      color: $text-secondary;
      transition: all .2s;
      white-space: nowrap;
      cursor: pointer;

      .material-icons {
        font-size: 24px;
      }

      &:hover {
        color: $text-primary;
        transform: scale(1.05);
      }

      &.bottom {
        margin-top: auto;
      }

      &.router-link-active {
        color: $text-primary;
        font-weight: bold;

        .material-icons {
          font-variation-settings: 'FILL' 1,
          'wght' 400,
          'GRAD' 0,
          'opsz' 40;
        }

        &:before {
          content: "";
          height: 6px;
          width: 6px;
          position: absolute;
          top: 5px;
          border-radius: 100%;
          background-image: $gradient;
        }
      }
    }
  }
}

@media only screen and (max-width: 600px) {

  .nav-container {
    height: fit-content;
    padding: 10px;
    width: 100vw;
    float: bottom;
    position: fixed;
    top: auto;
    bottom: 0;
    left: 0;
    right: 0;

    .nav-bar {
      background: $background-2;
      display: flex;
      flex-direction: row;
      padding: 10px;
      width: 100%;
      gap: 10px;
      border-radius: 18px;
      justify-content: space-between;

      .nav-item {
        padding: 0;
        height: auto;
        background: none;
        gap: 5px;

        &.router-link-active {
          &:before {
            display: none;
          }
        }
      }
    }
  }
}
</style>
