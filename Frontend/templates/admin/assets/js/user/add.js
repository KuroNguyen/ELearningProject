let token = localStorage.getItem("USER_TOKEN");

const loadRole = () => {
  let roleTag = document.getElementById("roleId");
  let fullname = document.getElementById("fullname");
  let content = "";
  // Call role api
  axios({
    url: "http://localhost:8080/api/admin/role",
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => {
      let roles = response.data;
      console.log(roles);

      fullname = response.data.fullname;

      roles.forEach((role) => {
        content += `
            <option value=${role.id}>${role.description}</option>
        `;
      });
      roleTag.innerHTML = content;
    })
    .catch((error) => console.log(error));
};
loadRole();

const getInputForm = () => {
  // Get user infor from form
  let password = document.getElementById("password").value;
  let passwordConfirm = document.getElementById("passwordConfirm").value;
  if (password !== passwordConfirm) {
    swal("Thất bại!", "Mật khẩu xác nhận không đúng", "error");
    return;
  }

  let email = document.getElementById("email").value;
  let fullname = document.getElementById("fullname").value;
  let avatar = document.getElementById("avatar").value;
  let roleId = document.getElementById("roleId").value;

  // Validate data
  let isValidated = true;
  if (fullname.length == 0) {
    document.getElementById("fullnameError").innerHTML = "Vui lòng nhập họ tên";
    isValidated = false;
  } else {
    document.getElementById("fullnameError").innerHTML = "";
  }
  if (email.length == 0) {
    document.getElementById("emailError").innerHTML = "Vui lòng nhập email";
    isValidated = false;
  } else if (
    /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(
      email
    ) === false
  ) {
    document.getElementById("emailError").innerHTML =
      "Email không đúng định dạng";
    isValidated = false;
  } else {
    document.getElementById("emailError").innerHTML = "";
  }
  if (password.length == 0) {
    document.getElementById("passwordError").innerHTML =
      "Vui lòng nhập password";
    isValidated = false;
  } else if (password.length < 6) {
    document.getElementById("passwordError").innerHTML =
      "Mật khẩu ít nhất 6 ký tự ";
    isValidated = false;
  } else {
    document.getElementById("passwordError").innerHTML = "";
  }
  if (passwordConfirm !== password) {
    document.getElementById("confirmError").innerHTML =
      "Mật khẩu xác nhận không đứng";
    isValidated = false;
  } else {
    document.getElementById("confirmError").innerHTML = "";
  }

  if (isValidated !== true) return;

  // Create json object store information
  let user = {
    avatar: avatar,
    email: email,
    fullname: fullname,
    password: password,
    roleId: roleId,
  };
  // Call insert api
  axios({
    url: "http://localhost:8080/api/admin/human",
    method: "POST",
    data: user,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => {
      console.log(response);
      swal("Thành công", "Thêm mới thành công!", "success").then(() => {
        document.location.href = "../../../admin/user/index.html";
      });
    })
    .catch((error) => {
      console.log({ error });
      swal("Thất bại", "Thêm mới thất bại!", "error");
    });
};
