const articleList = [];
const tags = [];

$.get(
	'article_list.json',
	{},
	function(data) {
		data.forEach((row, index) => {
			console.log(row);

			const article = {
				id: row.id,
				regDate: row.regDate,
				writer: row.extra__writer,
				title: row.title,
				body: row.body,
				hit: row.hit,
				likesCount: row.likesCount,
				commentsCount: row.commentsCount
			};

			articleList.push(article);
		});
	},
	'json'
);

$.get(
	'article_tag.json',
	{},
	function(data) {
		data.forEach((row, index) => {
			console.log(row);

			const tag = {
				Type: row.relTypeCode,
				id: row.relId,
				body: row.body
			};

			tags.push(tag);
		});
	},
	'json'
);

new Vue({
  el: "#articleTagList",
  data: {
    articleList:articleList,
    tags:tags,
    tag:''
  },
  methods: {
    update: _.debounce(function(e){
      this.tag = e.target.value;
    }, 500)
  },
  computed: {
    filterKey:function() {
      return this.tag.toLowerCase();
    },
    filtered: function() {
      if ( this.tag.length == 0 ) {
        return this.articleList;
      }
      
      return this.articleList.filter((row) => {
        if ( row.title.toLowerCase().indexOf(this.filterKey) > -1 ) {
          return true;
        }
      });
    }
  }
});