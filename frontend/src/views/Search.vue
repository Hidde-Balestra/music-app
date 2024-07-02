<script>
import axios from "axios";
import router from "@/router";
import MusicSample from "@/components/MusicSample";
import MusicSample2 from "@/components/MusicSample2";

export default {
  components: { 
    MusicSample,
    MusicSample2
  },
  data() {
    return {
      dataSearch: null,
      searchBar: window.location.pathname.replace("/search/", "")
    };
  },
  methods: {
    search() {
       return axios
            .get(`${this.$config.apiUrl}/search/samples`, {params: {query: this.searchBar}})
            .then((response) => {
              this.dataSearch = response.data
            })
            .catch((err) => {
              console.error({err});
            })
    },
    searchFieldOnUpdate(){
      console.log(this.searchBar);
      if (this.searchBar.length > 0){
        router.push({ path: '/search/'+this.searchBar })
        this.search()
      } else {
        router.push({ path: '/' })
      }
    }
  },
  created() {
    setTimeout(() => {
      document.getElementById('searchField').focus()
    }, 10)
  },
  beforeMount() {
    this.search()
  },
};

</script>

<template>
  <div>

    <div class="v-row">
      <div class="v-col-11">
        <div class="v-container">
          <div class="v-row">
            <div class="v-col-12">
              <h1>Explore</h1>
              <v-text-field 
                id="searchField" 
                v-model="searchBar" 
                @keyup="searchFieldOnUpdate($event)" 
                label="Search Sample"
                clearable
                append-inner-icon="mdi-magnify"
              ></v-text-field>

              <p class="line">Results</p>

              <div class="sampleContainer sampleContainer-6">
<!--                <MusicSample-->
<!--                    v-for="sample of dataSearch"-->
<!--                    :key="sample.idMusicSample"-->
<!--                    :sample="sample"-->
<!--                ></MusicSample>-->
                <music-sample2 v-for="sample of dataSearch" :sample="sample" :direction="'column'"/>
              </div>

            </div>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<style scoped lang="scss">
@import "../assets/styles/global.scss";

.v-col-12 {
  padding-block: 0;
}

p.line {
  position: relative;
  overflow: hidden;
  margin: 10px 0;

  &:after {
    content: "";
    display: inline-block;
    height: 11px;
    vertical-align: bottom;
    width: 100%;
    margin-right: -100%;
    margin-left: 10px;
    border-top: 1px solid white;
  }
}

.play-btn {
  background: $background-3;
  height: 50px;
  width: 50px;
  border-radius: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: .2s;

  .material-icons {
    font-size: 30px;
  }

  &:hover {
    cursor: pointer;
    transform: scale(1.1);

    .material-icons {
      font-variation-settings: 'FILL' 1,
      'wght' 400,
      'GRAD' 0,
      'opsz' 24;
    }
  }
}

.most-played {
  display: flex;
  padding: 20px;
  gap: 20px;
}

.most-played, .player {
  position: relative;
  background: #1A1A1A;
  border-radius: 10px;
  margin-right: 10px;
  margin-bottom: 10px;

  .song-img {
    background: #2A2A2A;
    width: 100px;
    border-radius: 100px;
    margin: 0 auto;

    img {
      width: 100px;
    }
  }
}

.most-played .song-info {
  top: 18%;
  display: inline-block;

}

.most-played .song-img {
  display: inline-block;
  margin: 10px 16px;
}
</style>
