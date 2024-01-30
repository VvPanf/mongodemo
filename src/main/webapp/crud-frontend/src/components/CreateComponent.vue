<template>
    <div v-if="Object.keys(headers).length !== 0">
        <section class="content-section">
            <div v-for="header in headers" class="input-line">
                <label class="input-line__label">{{ header }}</label>
                <input type="text" v-model="values[header]">
            </div>
            <div>
                <button v-on:click="createBook()">Создать</button>
            </div>
        </section>
    </div>
    <div v-else>
        <section class="content-section">
            <p>Не удалось прочитать поля</p>
        </section>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                values: {}
            }
        },
        computed: {
            headers() {
                if (this.$store.getters.getBooks.length) {
                    return Object.keys(this.$store.getters.getBooks[0]);
                } else {
                    return [];
                }
            },
        },
        methods: {
            createBook() {
                this.$store.dispatch('saveBook', this.values);
            }
        }
    };
</script>