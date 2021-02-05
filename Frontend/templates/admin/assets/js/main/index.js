// KIỂM TRA ĐĂNG NHẬP
function checkLogin() {
    // LẤY TOKEN TỪ LOCALSTORAGE
    let token = localStorage.getItem('USER_TOKEN');
    if(token == null || token.length == 0){
        document.location.href = "../auth/login.html";
    }
}



// ĐĂNG XUẤT
function logout() {
    // LẤY TOKEN TỪ LOCALSTORAGE
    let token = localStorage.getItem('USER_TOKEN');
    if(token != null){
        // XÓA TOKEN KHỎI LOCALSTORAGE
        localStorage.removeItem('USER_TOKEN');
        document.location.href = "../auth/login.html";
    }
}


checkLogin();
