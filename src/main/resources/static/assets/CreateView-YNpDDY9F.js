import{m as _,_ as k,o as t,c as s,a as o,F as p,r as h,b as d,d as v,t as m,w as B,v as b,e as c,U as y}from"./index-oP5Z4TcX.js";const g={data(){return{values:{}}},computed:{..._({book:"book"}),headers(){return this.$store.getters.getBooks.length?Object.keys(this.$store.getters.getBooks[0]):[]}},watch:{book:function(l,e){this.values=l}},methods:{getBookById(){this.$store.dispatch("loadBookById",this.values.id)},createBook(){this.$store.dispatch("saveBook",this.values)},updateBook(){this.$store.dispatch("updateBook",this.values)}}},f={key:0},C={class:"content-section"},x={class:"input-line"},V={class:"input-line__label"},w=["disabled"],N=["onUpdate:modelValue"],U={class:"input-buttons"},$={key:1},I=o("section",{class:"content-section"},[o("p",null,"Не удалось прочитать поля")],-1),j=[I];function D(l,e,u,O,i,a){return Object.keys(a.headers).length!==0?(t(),s("div",f,[o("section",C,[(t(!0),s(p,null,h(a.headers,n=>(t(),s("div",x,[o("label",V,[v(m(n)+" ",1),n=="id"?(t(),s("button",{key:0,disabled:!("id"in i.values),class:"input-line__button",onClick:e[0]||(e[0]=r=>a.getBookById())},"->",8,w)):d("",!0)]),B(o("input",{type:"text","onUpdate:modelValue":r=>i.values[n]=r},null,8,N),[[b,i.values[n]]])]))),256)),o("div",U,[o("button",{onClick:e[1]||(e[1]=n=>a.createBook())},"Создать"),i.values.id?(t(),s("button",{key:0,onClick:e[2]||(e[2]=n=>a.updateBook())},"Обновить")):d("",!0)])])])):(t(),s("div",$,j))}const F=k(g,[["render",D]]),T={__name:"CreateView",setup(l){return(e,u)=>(t(),s("main",null,[c(y),c(F)]))}};export{T as default};