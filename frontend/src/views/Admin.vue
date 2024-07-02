<script>
import NavBar from "@/components/Nav-bar.vue";
import axios from "axios";

export default {
  components: {NavBar},
  data() {
    return {
      userReportsData: {},
      sampleReportsData: {},
    };
  },
  methods: {
    userReports() {
      return axios
          .get(`${this.$config.apiUrl}/reported-users`)
          .then((response) => {
            this.userReportsData = response.data
          })
          .catch((err) => {
            console.error({err});
          })
    },
    samplesReports() {
      return axios
          .get(`${this.$config.apiUrl}/reported-samples`)
          .then((response) => {
            this.sampleReportsData = response.data
          })
          .catch((err) => {
            console.error({err});
          })
    },
    banUser(id) {
      axios.put(`${this.$config.apiUrl}/disable-user/${id}`)
          .then(response => {
            // Handle the success response
            console.log('PUT request successful:', response.data);
            location.reload();
          })
          .catch(error => {
            // Handle errors
            console.error('Error making PUT request:', error);
          });
    },
    unBanUser(id) {
      axios
          .put(`${this.$config.apiUrl}/enable-user/${id}`)
          .then(response => {
            // Handle the success response
            console.log('PUT request successful:', response.data);
            location.reload();
          })
          .catch(error => {
            // Handle errors
            console.error('Error making PUT request:', error);
          });
    },
    banSample(id) {
      axios.put(`${this.$config.apiUrl}/disable-sample/${id}`)
          .then(response => {
            // Handle the success response
            console.log('PUT request successful:', response.data);
            location.reload();
          })
          .catch(error => {
            // Handle errors
            console.error('Error making PUT request:', error);
          });
    },
    unBanSample(id) {
      axios
          .put(`${this.$config.apiUrl}/enable-sample/${id}`)
          .then(response => {
            // Handle the success response
            console.log('PUT request successful:', response.data);
            location.reload();
          })
          .catch(error => {
            // Handle errors
            console.error('Error making PUT request:', error);
          });
    }
  },
  created() {
    this.userReports()
    this.samplesReports()
  },
};
</script>

<template>

  <div class="v-row">
    <div class="v-container">
      <h1>Admin</h1>

      <div class="reported-samples">
        <p class="line">Reported Samples</p>
        <div class="v-row">
          <div class="v-col-12" v-for="dataSample in sampleReportsData">
            <h2>{{ dataSample.sampleName }}</h2>
            <p>{{ dataSample.sampleDescription }}</p>
            <div v-for="dataUserReport in dataSample.reports">
              <p>{{ dataUserReport.dateReport }} - {{ dataUserReport.message }}</p>
            </div>
            <div class="action-buttons v-row">
              <button v-if="dataSample.active" @click="banSample(dataSample.idMusicSample)" class="primary-button v-col-4 v-col-lg-2">Remove Sample</button>
              <button v-if="!dataSample.active" @click="unBanSample(dataSample.idMusicSample)" class="primary-button v-col-4 v-col-lg-2">Enable Sample</button>
              <button class="primary-button v-col-2 v-col-lg-1">X</button>
            </div>
          </div>
        </div>
      </div>

      <div class="reported-users">
        <p class="line">Reported Users</p>
        <div class="v-col-12" v-for="dataUser in userReportsData">
          <h2>{{ dataUser.firstName }} {{ dataUser.lastName }}</h2>
          <div v-for="dataUserReport in dataUser.reports">
            <p>{{ dataUserReport.dateReport }} - {{ dataUserReport.message }} Reported by
              {{ dataUserReport.reportingUser.firstName }} {{ dataUserReport.reportingUser.lastName }}</p>
          </div>
          <div class="action-buttons v-row">
            <button v-if="dataUser.active" @click="banUser(dataUser.idUser)" class="primary-button v-col-4 v-col-lg-2">Ban user</button>
            <button v-if="!dataUser.active" @click="unBanUser(dataUser.idUser)" class="primary-button v-col-4 v-col-lg-2">Unban user</button>
            <button class="primary-button v-col-2 v-col-lg-1">X</button>
          </div>
        </div>
      </div>

      <!--      <div class="find-user">-->
      <!--        <p class="line">Find User</p>-->
      <!--        <v-text-field label="Find User"></v-text-field>-->
      <!--      </div>-->

    </div>
  </div>
</template>

<style scoped lang="scss">

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

.action-buttons {
  margin-top: 10px;
}
</style>
