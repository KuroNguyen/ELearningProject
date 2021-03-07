let userProfile = JSON.parse(localStorage.getItem("USER_INFO"));

// Get token from localStorage
let token = localStorage.getItem("USER_TOKEN");
const loadProfile = () => {
  // Call getUserById api
  axios({
    url: `http://localhost:8080/api/admin/human/${userProfile.id}`,
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log(resp);
      userModel = resp.data;

      document.getElementById("nameProfile").innerHTML = userModel.fullname;
      document.getElementById("emailProfile").innerHTML = userModel.email;
      document.getElementById("securityEmail").value = userModel.email;

      document.getElementById("Email").value = userModel.email;
      document.getElementById("FullName").value = userModel.fullname;
      document.getElementById("Address").value = userModel.address;
      document.getElementById("Phone").value = userModel.phone;
    })
    .catch((error) => {
      console.log({ error });
    });
};

function apiEdit(user) {
  return new Promise(
    axios({
      url: `http://localhost:8080/api/admin/human/profile/${user.id}`,
      method: "PUT",
      data: user,
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((resp) => {
        console.log(resp);
        loadProfile();
      })
      .catch((error) => {
        console.log(error);
      })
  );
}

async function saveProfile() {
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

  axios({
    url: `http://localhost:8080/api/human/profile/${user.id}`,
    method: "PUT",
    data: user,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log(resp);
      loadProfile();
      swal("Thành Công", "Cập nhật thành công", "success").then(() => {
        document.location.href = "../Elearning/profile.html";
      });
    })
    .catch((error) => {
      console.log(error);
      swal("Thất bại", "Cập nhật thất bại!", "error");
    });
}

// Change password

const changePassword = () => {
  let flag = true;
  let password = document.getElementById("password").value;
  let confirm = document.getElementById("confirm").value;

  //let password = document.getElementById('rgPassword').value;
  if (password.length == 0) {
    flag = false;
    document.getElementById("password").innerHTML = "Vui lòng nhập mật khẩu!";
  } else if (password.length < 6) {
    flag = false;
    document.getElementById("passwordERR").innerHTML =
      "Mật khẩu ít nhất 6 ký tự!";
  } else {
    document.getElementById("passwordERR").innerHTML = "";
  }

  //let confirm = document.getElementById('rgConfirm').value;
  if (confirm.length == 0) {
    flag = false;
    document.getElementById("confirmERR").innerHTML =
      "Vui lòng nhập lại mật khẩu!";
  } else if (confirm !== password) {
    flag = false;
    document.getElementById("confirmERR").innerHTML =
      "Nhập lại mật khẩu không khớp!";
  } else {
    document.getElementById("confirmERR").innerHTML = "";
  }

  let id = userProfile.id;
  if (flag === true) {
    let pass = {
      id: id,
      password: password,
    };

    axios({
      url: `http://localhost:8080/api/admin/human/password/${id}`,
      method: "PUT",
      data: pass,
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((reponse) => {
        console.log(reponse);

        swal(
          "Cập nhật password thành công",
          "Cập nhật thành công",
          "success"
        ).then(() => {
          document.location.href = "../Elearning/profile.html";
        });
      })
      .catch((error) => {
        console.log(error);

        swal("Cập nhật password thất bại", "Thêm mới thất bại!", "error");
      });
  }
};

loadProfile();
