<template>
  <div class="home-content">
    <h1>Explore</h1>
    <v-text-field
        id="searchField"
        v-model="search"
        @keyup="searchFieldOnUpdate($event)"
        label="Search Sample"
        clearable
        append-inner-icon="mdi-magnify"
    ></v-text-field>
    <div v-if="doneLoading">
      <p class="line">Most played sample of the week</p>

      <div class="highlighted-sample">
        <music-sample2  :sample="samples.mostPlayedThisWeek" only-play-btn/>
        <div>
          <h2>{{samples.mostPlayedThisWeek?.sampleName}}</h2>
          <h3><a :href="'/profile/' + samples.mostPlayedThisWeek?.user?.idUser">{{samples.mostPlayedThisWeek?.user?.firstName}} {{samples.mostPlayedThisWeek?.user?.lastName}}</a></h3>
        </div>
        <div class="labels">
          <div class="label" v-for="sampleLabel of samples.mostPlayedThisWeek.labels" :key="sampleLabel">{{sampleLabel.name}}</div>
        </div>
      </div>

      <p class="line">Recommended</p>

      <div class="sampleContainer sampleContainer-6">
        <music-sample2 v-for="sample of samples.recommended" :sample="sample" :direction="'column'" :key="sample"/>
      </div>

      <p class="line">Recently played</p>

      <div class="sampleContainer sampleContainer-6">
        <music-sample2 v-for="sample of samples.recentlyPlayed" :sample="sample" :direction="'column'" :key="sample"/>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";
import MusicSample2 from "@/components/MusicSample2";

export default {
  components: {
    MusicSample2
  },
  created() {

    Promise.all([
      this.getMostPlayedThisWeek(),
      this.getRecommended(),
      this.getRecentlyPlayed(),
    ])
        .then(() => {
          this.doneLoading = true;

          console.log(
              JSON.parse(JSON.stringify(this.samples))
          );
        })
        .catch((err) => {
          console.error({err});
        })

    setTimeout(() => {
      document.getElementById('searchField').focus()
    }, 10);

  },
  data() {
    return {

      search: null,

      samples: {
        mostPlayedThisWeek: null,
        recommended: [],
        recentlyPlayed: []
      },

      doneLoading: false,

    }
  },
  methods: {

    // Api calls

    getMostPlayedThisWeek(){
      return new Promise((resolve, reject) => {
        axios
            .get(`${this.$config.apiUrl}/samples/most_played_this_week`)
            .then((response) => {
              console.log({response});
              this.samples.mostPlayedThisWeek = response.data;
              resolve();
            })
            .catch((err) => {
              console.error({err});
              resolve([]);
            })
      });
    },

    getRecommended() {
      const userId = localStorage.getItem('currentUserId');
      const urlEndpoint = (userId) ? `/recommendation/${userId}` : `/samples/recommended`;

      return new Promise((resolve, reject) => {
        axios
            .get(`${this.$config.apiUrl}${urlEndpoint}`)
            .then((response) => {
              console.log({response});
              this.samples.recommended = response.data;
              resolve();
            })
            .catch((err) => {
              console.error({err});
              resolve([]);
            })
      });
    },

    getRecentlyPlayed() {
      return new Promise((resolve, reject) => {
        axios
            .get(`${this.$config.apiUrl}/samples/recently_played`)
            .then((response) => {
              console.log({response});
              this.samples.recentlyPlayed = response.data;
              resolve();
            })
            .catch((err) => {
              console.error({err});
              resolve([]);
            })
      });
    },

    // Event handlers

    searchFieldOnUpdate() {
      if (this.search) router.push({path: 'search/' + this.search})
    },

  }
}
</script>

<style lang="scss">
@import "../assets/styles/global.scss";

.home-content {
  display: flex;
  flex-direction: column;
  gap: 20px;

  h1 {
    line-height: 1
  }

  .highlighted-sample {
    width: 100%;
    background: $background-2;
    display: flex;
    padding: 20px;
    align-items: center;
    gap: 10px;
    align-self: stretch;
    border-radius: 16px;

    > div {
      width: 100%;
    }

    .labels {
      display: flex;
      gap: 5px;
      flex-wrap: wrap;

      .label {
        border-radius: 345px;
        padding: 4px 8px;
        background: $background-3;
      }
    }

    .play-btn {
      height: 100px;
      width: 100px;
      min-width: 100px;

      .material-icons {
        font-size: 60px;
      }
    }
  }
}
</style>