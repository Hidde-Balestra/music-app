

<template>
    <div>
        <!-- attributes with ':' infront allow for javascript code -->
        <!-- outside of attributes {{...}} does the job -->
        <!-- list of mdi icons: https://pictogrammers.com/library/mdi/ -->
        <i class="mdi" :class="`mdi-${icon}`"></i> {{title}}

        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Studentnumber</th>
                </tr>
            </thead>
            <tbody>
                <!-- example of v-for -->
                <tr v-for="user in users.filter(e => e.display)" :key="user.studentnumber">
                    <td>{{user.name}}</td>
                    <td>{{user.studentnumber}}</td>
                </tr>
            </tbody>
        </table>

        <div class="form">
            <!-- vuetify provides useful components (like v-autocomplete and v-btn) -->
            <!-- https://vuetifyjs.com/en/introduction/why-vuetify/#what-is-vuetify3f -->
            <!-- v-model holds input's value -->
            <v-autocomplete v-model="selectedUser" :items="users" label="User" item-title="name" item-value="studentnumber"></v-autocomplete>
            <!-- click event -->
            <v-btn @click="sendForm" color="primary">Submit</v-btn>
            <!-- v-if -->
            <div v-if="selectedUser != null">
                Selected: {{selectedUser}}
            </div>
        </div>


    </div>
</template>

<script>

export default {
    created() { // Gets called upon creation
        console.log('Example loaded')
    },
    props: { // Parent component / view is able to provide props    
        title: {
            type: String,
            default: "default title",
        },
    },
    data() { // component data
        return {
            icon: "home",
            users: [
                {name: 'Djangesh', studentnumber: '123', display: true},
                {name: 'Hidde', studentnumber: '234', display: true},
                {name: 'Yorick', studentnumber: '345', display: true},
                {name: 'Stephen', studentnumber: '456', display: true},
                {name: 'Tico', studentnumber: '567', display: true},
                {name: 'Corniels', studentnumber: '678', display: true},
                {name: 'Sukkel', studentnumber: '999', display: false},
            ],
            selectedUser: null,
        };
    },
    methods: { // component methods
        sendForm(){
            const user = this.users.find(u => u.studentnumber == this.selectedUser);
            alert(JSON.stringify(user));
        }
    },
};
</script>

<!-- scss with lang="scss" -->
<!-- 'scoped' limits the css to this component -->
<style lang="scss" scoped>

    table{
        background: #f1f1f1;
        
        th{
            border-bottom: 1px solid #000;
        }
    }

    .form{
        margin: 15px;
        padding: 15px;
        border: 1px solid rgb(144, 144, 144);
    }

</style>