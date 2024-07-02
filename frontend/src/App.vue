<template>
  <div id="appContainer" :class="showNavBar ? 'hasNavBar' : ''">
    <nav-bar v-if="showNavBar"></nav-bar>
    <router-view class="main" :key="$route.fullPath" :class="{playerActive: currentAudio}"></router-view>
    <player :currentAudio="currentAudio"/>
  </div>
</template>

<script>
import {computed} from 'vue';
import {useRouter} from 'vue-router';
import NavBar from "@/components/Nav-bar";
import Player from "@/components/Player";

if (/Chrome/.test(navigator.userAgent)) {
  document.body.classList.add('chrome');
}

export default {
  components: {NavBar, Player},
  setup() {
    const router = useRouter();

    const showNavBar = computed(() => {
      const pathsToHideNavBar = ['/login', '/register'];
      return !pathsToHideNavBar.includes(router.currentRoute.value.path);
    });

    return {showNavBar};
  },
  computed: {
    currentAudio() {
      return this.$store.state.currentAudio;
    },
  },

}
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200');
</style>

<style lang="scss" scoped>
#appContainer {
  position: absolute;
  top: 0;
  left: 0;
  width: 100vw;
  min-height: 100vh;
  height: fit-content;

  &.hasNavBar {
    padding-left: 140px;

    @media (max-width: 600px) {
      padding-left: unset;
      padding-bottom: 100px;

    }

    &.main {
      padding-top: 20px;
    }
  }

  .main.playerActive {
    padding-bottom: 106px;
  }

  .main {
    padding: 20px 40px;

    @media (max-width: 600px) {
      padding: 20px;
    }
  }
}
</style>