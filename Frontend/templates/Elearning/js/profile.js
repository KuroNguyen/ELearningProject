// Load userProfile from localStorage
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

      // Load avatar
      if (userModel.avatar) {
        document.getElementById("imgUrl").value = userModel.avatar;
        document.getElementById(
          "imgAvatar"
        ).src = `http://localhost:8080/${userModel.avatar}`;
      }
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
  let avatar = document.getElementById("imgUrl").value;

  // Create user object
  let user = {
    id: id,
    email: email,
    fullname: fullname,
    address: address,
    phone: phone,
    avatar: avatar,
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
  let id = userProfile.id;
  let flag = true;
  let oldPassword = document.getElementById("oldPassword").value;
  let confirm = document.getElementById("confirm").value;
  let newPassword = document.getElementById("newPassword").value;
  let email = document.getElementById("securityEmail").value;

  //let password = document.getElementById('rgPassword').value;
  if (newPassword.length == 0) {
    flag = false;
    document.getElementById("newPasswordERR").innerHTML =
      "Vui lòng nhập mật khẩu!";
  } else if (newPassword.length < 6) {
    flag = false;
    document.getElementById("newPasswordERR").innerHTML =
      "Mật khẩu ít nhất 6 ký tự!";
  } else {
    document.getElementById("newPasswordERR").innerHTML = "";
  }

  //let confirm = document.getElementById('rgConfirm').value;
  if (newPassword.length == 0) {
    flag = false;
    document.getElementById("confirmERR").innerHTML =
      "Vui lòng nhập lại mật khẩu!";
  } else if (confirm !== newPassword) {
    flag = false;
    document.getElementById("confirmERR").innerHTML =
      "Nhập lại mật khẩu không khớp!";
  } else {
    document.getElementById("confirmERR").innerHTML = "";
  }

  if (flag === true) {
    let checkPass = {
      id: id,
      email: email,
      oldPassword: oldPassword,
      newPassword: newPassword,
    };

    axios({
      url: `http://localhost:8080/api/human/changePassword`,
      method: "POST",
      data: checkPass,
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        console.log(response);

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

// Upload avatar
const uploadAvatar = () => {
  // Reference avatar
  let imageInput = document.getElementById("avatar");

  // Add file to form data
  let formData = new FormData();
  formData.append("file", imageInput.files[0]);

  // Call upload file api
  axios({
    url: "http://localhost:8080/api/file/upload",
    method: "POST",
    data: formData,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      let imgUrl = resp.data;
      document.getElementById("imgUrl").value = imgUrl;
      // Load image to imgAvatar
      document.getElementById(
        "imgAvatar"
      ).src = `http://localhost:8080/${imgUrl}`;
    })
    .catch((error) => {
      console.log({ error });
    });
};
