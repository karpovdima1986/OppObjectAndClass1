data class Post(
    val id: Int,//Идентификатор записи.
    val owner_id: Int,//Идентификатор владельца стены, на которой размещена запись.
    // В версиях API ниже 5.7 это поле называется to_id.
    val from_id: Int,//Идентификатор автора записи (от чьего имени опубликована запись).
    val created_by: Boolean = false,//Идентификатор администратора, который опубликовал запись
//    // (возвращается только для сообществ при запросе с ключом доступа администратора).
//    // Возвращается в записях, опубликованных менее 24 часов назад.
    val date: Date = Date(), //Время публикации записи в формате unixtime.
    val text: String,//Текст записи.
    val reply_owner_id: Int,//Идентификатор владельца записи, в ответ на которую была
//    // оставлена текущая.
    val reply_post_id: Int,//Идентификатор записи, в ответ на которую была оставлена текущая.
    val friends_only: Boolean = false,// 1, если запись была создана с опцией «Только для друзей».
    var likes: Likes = Likes(),
)





data class Likes(var likes: Int = 0)
data class Date(val date: Long = System.currentTimeMillis()/ 1000) //String =java.time.format.DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(1712658895))) //
//data class FriendsOonly( val friends_only = if (friends_only == 1)"только для друзей"
// else true
// )





object  WallService{
    var posts = emptyArray<Post>()
    private var lastPostId = 0

    fun add(post: Post,): Post{
        posts += post.copy (id = ++ lastPostId, likes = post.likes.copy(), date = post.date.copy(), )
        return posts.last()
    }

    fun update (newPost : Post) : Boolean {
        for((index, post) in posts.withIndex()){
            if(post.id == newPost.id) {
                posts [index] = newPost.copy(likes = post.likes.copy(), date = post.date.copy(),)
                return true
            }
        }
        return false
    }

    fun printPosts(){
        for(post in posts){
            print(post)
            print(" ")

        }
        println()
    }

}


fun main () {

    WallService.add(Post(1,1,24,false, date = Date(1712744678)  ,"Hi", 2,35, true ,Likes(5) ))


    WallService.add(Post(1,2,23,true, date = Date(),"Hello",3, 4,false, Likes(15)))
    WallService.printPosts()
    WallService.update(Post(1,3,23,true, date = Date(),"New text",3, 4,false, Likes(15), ))
    WallService.printPosts()
    println(WallService.update(Post(1,3,23,true, date = Date(),"New text",3, 4,false, Likes(15), )))

    println(WallService.update(Post(100,3,23,true, date = Date(),"New text",3, 4,false, Likes(15), )))

}