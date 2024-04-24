
import WallService.add
import org.junit.Test

import org.junit.Assert.*

class WallServiceTest {
    @Test
    fun testAdd() {
       val id = 0
        val owner_id = 3
        val from_id = 4
        val created_by = false
        val text = "Hello"
        val reply_owner_id = 5
        val reply_post_id= 6


       val result =  add(Post(
            id,
            owner_id,
            from_id,
            created_by,
            date = Date(1712744678),
            text,
            reply_owner_id,
            reply_post_id,
            friends_only = false,likes= Likes(2),))

        assertEquals(1, result)
    }
}