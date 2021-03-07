
function dangKi() {
    var fullname = getElm("rgName").value;
    var email = getElm("rgEmail").value;
    var password = getElm("rgPassword").value;
    var confirm = getElm("rgConfirm").value;
    //  console.log(name)

    //  if(name===""){
    //      getElm("fullnameErr").innerHTML = "lol me";
    //         console.log(name)
    //  }
    //  else{

    //      console.log("thanh  cong")
    //      getElm("thoats").click();
    //  }

    let flag = true;
    //let fullname = document.getElementById('rgName').value;
    if (fullname.length == 0) {
        flag = false;
        document.getElementById('fullnameErr').innerHTML = 'Vui lòng nhập họ tên!';
    }
    else {
        document.getElementById('fullnameErr').innerHTML = '';
    }

    //let email = document.getElementById('rgEmail').value;
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

    //let password = document.getElementById('rgPassword').value;
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

    //let confirm = document.getElementById('rgConfirm').value;
    if (confirm.length == 0) {
        flag = false;
        document.getElementById('confirmErr').innerHTML = 'Vui lòng nhập lại mật khẩu!';

    }
    else if (confirm !== password) {
        flag = false;
        document.getElementById('confirmErr').innerHTML = 'Nhập lại mật khẩu không khớp!';
        console.log("Chạy chưa @@");
    }else if(kiemTraEmail(email)===false){
        document.getElementById('emailErr').innerHTML = 'Email da duoc su dung ';

    }
    else {
        document.getElementById('confirmErr').innerHTML = '';
    }



    if (flag === true) {

        let user = {
            "fullname": fullname,
            "email": email,
            "password": password,


        }

        axios({
            url: 'http://localhost:8080/api/auth/register',
            method: 'POST',
            data: user


        })
            .then(function (resp) {
                console.log('Success');

                swal("Thành công", "Thêm mới thành công!", "success").then(() => {
                    document.location.href = "./../Elearning/index.html";
                    
                });
                document.getElementById("btnThoat").click();
            
            })
            .then(function (err) {
                console.log(err);
                console.log('Thất bại!');

            })
    } else {
        return;
    }

}



document.getElementById("btnDangKi").addEventListener("click", function () {
    dangKi();

});

function kiemTraEmail(email){

    let emailtest ={
        taiKhoan: email
    }

    axios({
        url: 'http://localhost:8080/api/auth/kiemTraEmail',
        method: 'POST',
        data: emailtest


    }).then((resual)=>{
        //back end kiem tra chua co email nao duoc su dung , tra ve ket qua true
        if(resual.data.kiemTraEmail === true){
            return true;
        }else{
            return false;
        }
      
    }).catch((err)=>{
        console.log(err)
    })
}


function getElm(id) {
    return document.getElementById(id);
}