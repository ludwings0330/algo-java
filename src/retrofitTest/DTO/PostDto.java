package retrofitTest.DTO;

import java.util.List;

public class PostDto {
    public static class PostList {
        List<Post> posts;

        public List<Post> getPosts() {
            return posts;
        }

        public void setPosts(List<Post> posts) {
            this.posts = posts;
        }
    }

    public static class Post {
        Long id;
        String title;
        String body;
        Long userId;


        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("id = ").append(this.id).append('\n');
            sb.append("title = ").append(this.title).append('\n');
            sb.append("body = ").append(this.body).append('\n');
            sb.append("userId = ").append(this.userId).append('\n');
            return sb.toString();
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }

    public static class Request {
        String title;
        String body;
        Long userId;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }
}
