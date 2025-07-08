package com.onlinebookstore.OnlineBookstore.DTO.OrderItemDTO;

public class OrderItemDTO {
    public static class UserLoginDTO {
        private String usernameOrEmail;
        private String password;

        public UserLoginDTO() {}

        public UserLoginDTO(String usernameOrEmail, String password) {
            this.usernameOrEmail = usernameOrEmail;
            this.password = password;
        }

        public String getUsernameOrEmail() {
            return usernameOrEmail;
        }

        public void setUsernameOrEmail(String usernameOrEmail) {
            this.usernameOrEmail = usernameOrEmail;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
