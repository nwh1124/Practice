const articleList = [];

let params = location.search.substr(location.search.indexOf("?") + 1);
let searchTag = params.substr(4);

$.get(
	'article_tag.json',
	{},
	function(data) {
		data.forEach((row, index) => {
		        const articleWithTags = {
              id: row.id,
              regDate: row.regDate,
              title: row.title,
              body: row.body,
              hit: row.hitsCount,
              likesCount: row.likesCount,
              commentsCount: row.commentsCount,
              tags: row.extra__tags
		      }; 
		    articleList.push(articleWithTags);				
		});
	},
	'json'
);

new Vue({
    el: "#articleTagList",
  data: {
    articleList:articleList,
    searchTag:searchTag
  },
  computed: {
    filterKey:function() {
      return this.searchTag;
    },
    filtered: function() {
      
      return this.articleList.filter((row) => {
        if ( row.tags.indexOf(this.filterKey) > -1 ) {
          return true;
        }
      });
    }
  }
});