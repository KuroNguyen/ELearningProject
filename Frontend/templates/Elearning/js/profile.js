let userProfile = JSON.parse(localStorage.getItem("USER_INFO"));

let token = localStorage.getItem("USER_TOKEN");
const loadProfile = () => {
  // Get user data

  console.log(userProfile);

  let profile = document.getElementById("labelProfile");
  document.getElementById("securityEmail").value = userProfile.email;

  // document.getElementById("nameProfile").value = userProfile.email;
  // document.getElementById("emailProfile").value = userProfile.email;

  let content =
    `<h1 id="nameProfile">${userProfile.name}</h1>
        <h5 id="emailProfile">${userProfile.email}</h5>`

  document.getElementById("Email").value = userProfile.email;
  document.getElementById("FullName").value = userProfile.name;
  document.getElementById("Address").value = userProfile.address;
  document.getElementById("Phone").value = userProfile.phone;

  profile.innerHTML = content;

};



//  let url = new URL(document.location.href);
//  let id = url.searchParams.get("id");

function apiEdit(user){


  return new Promise( axios({
    url: `http://localhost:8080/api/admin/human/profile/${user.id}`,
    method: "PUT",
    data: user,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  }))

  
  localStorage.setItem("USER_INFO", JSON.stringify(userProfile));

  userProfile = JSON.parse(localStorage.getItem("USER_INFO"));
}


async function  saveProfile  () {
  // Get user infor from form
  // let password = document.getElementById("password").value;
  // let passwordConfirm = document.getElementById("passwordConfirm").value;
  // if (password !== passwordConfirm) {
  //   swal("Thất bại!", "Mật khẩu xác nhận không đúng", "error");
  //   return;
  // }


  let id = userProfile.id;


  let email = document.getElementById("Email").value;
  let fullname = document.getElementById("FullName").value;
  let address = document.getElementById("Address").value;
  let phone = document.getElementById("Phone").value;

  // Create user object
  let user = {
    id: id,
    email: email,
    fullname: fullname,
    address: address,
    phone: phone,

  };

  console.log(user);

  // Call api update
 await apiEdit(user)
    .then((reponse) => {
      console.log({ reponse });
      // console.log(user.reponse.phone)


      // userProfile = {
      //   id: reponse.data.userId,
      //   fullname: reponse.data.userName,
      //   email: reponse.data.email,
      //   address: reponse.data.address,
      //   phone: reponse.data.phone


      // }

      console.log(id);
      console.log(fullname)
      console.log(email)
      console.log(address)
      console.log(phone)


      swal("Thành Công", "Cập nhật thành công", "success").then(() => {
        document.location.href = "../Elearning/profile.html";
      });

      // localStorage.getItem("USER_INFO", JSON.stringify(userProfile));
     

      loadProfile();





    })
    .catch((error) => {
      console.log(error);
      swal("Thất bại", "Thêm mới thất bại!", "error");
    });
};


// Change password 

const changePassword = () => {

  let flag = true;
   let password = document.getElementById("password").value;
  let confirm = document.getElementById("confirm").value;


  //let password = document.getElementById('rgPassword').value;
  if (password.length == 0) {
    flag = false;
    document.getElementById('password').innerHTML = 'Vui lòng nhập mật khẩu!';

  }
  else if (password.length < 6) {
    flag = false;
    document.getElementById('passwordERR').innerHTML = 'Mật khẩu ít nhất 6 ký tự!';

  } else {
    document.getElementById('passwordERR').innerHTML = '';
  }


  //let confirm = document.getElementById('rgConfirm').value;
  if (confirm.length == 0) {
    flag = false;
    document.getElementById('confirmERR').innerHTML = 'Vui lòng nhập lại mật khẩu!';

  }
  else if (confirm !== password) {
    flag = false;
    document.getElementById('confirmERR').innerHTML = 'Nhập lại mật khẩu không khớp!';
   
  }
  else {
    document.getElementById('confirmERR').innerHTML = '';
  }

  let id = userProfile.id;
  if (flag === true) {

    let pass = {
      "id":id,
      "password":password
    }


    axios({
      url: `http://localhost:8080/api/admin/human/password/${id}`,
      method: "PUT",
      data: pass,
      headers: {
        Authorization: `Bearer ${token}`,
      },


    }).then((reponse) => {

        console.log(reponse)

        swal("Cập nhật password thành công", "Cập nhật thành công", "success").then(() => {
          document.location.href = "../Elearning/profile.html";
        });




    })
    .catch((error) =>{

        console.log(error);

        swal("Cập nhật password thất bại", "Thêm mới thất bại!", "error");
    


    })

  }


}

loadProfile();