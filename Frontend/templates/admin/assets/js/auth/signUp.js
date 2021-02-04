function signUp(){


     let flag = true;

    let fullname = document.getElementById('rgName').value;
    if (fullname.length == 0) {
        flag = false;
        document.getElementById('fullnameErr').innerHTML = 'Vui lòng nhập họ tên!';
    }
    else {
        document.getElementById('fullnameErr').innerHTML = '';
    }

    let email = document.getElementById('rgEmail').value;
    if (email.length == 0) {
        flag = false;
        document.getElementById('emailErr').innerHTML = 'Vui lòng nhập email!';
    }
    else if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(email) === false) {
        flag = false;
        document.getElementById('emailErr').innerHTML = 'Email không đúng định dạng!';
    }
    else {
        document.getElementById('emailErr').innerHTML = '';
    }

    let password = document.getElementById('rgPassword').value;
    if (password.length == 0) {
        flag = false;
        document.getElementById('passwordErr').innerHTML = 'Vui lòng nhập mật khẩu!';
    }
    else if (password.length < 6) {
        flag = false;
        document.getElementById('passwordErr').innerHTML = 'Mật khẩu ít nhất 6 ký tự!';
    }
    else {
        document.getElementById('passwordErr').innerHTML = '';
    }

    let confirm = document.getElementById('rgConfirm').value;
    if (confirm.length == 0) {
        flag = false;
        document.getElementById('confirmErr').innerHTML = 'Vui lòng nhập lại mật khẩu!';
    }
    else if (confirm !== password) {
        flag = false;
        document.getElementById('confirmErr').innerHTML = 'Nhập lại mật khẩu không khớp!';
    }
    else {
        document.getElementById('confirmErr').innerHTML = '';
    }

    if(flag === true){

        let user = {
            "fullname": fullname,
            "email": email,
            "password": password,
            "confirm": confirm,
        }

        axios({
            url:'http://localhost:8080/api/admin/user',
            method: 'POST',
            data: user


        })
        .then(function(resp){
            console.log('Success');
            document.location.href = "../../Elearning/index.html";
        })
        .then(function(err){
            console.log(err.response);
            console.log('Thất bại!');
           
        })
    }


}