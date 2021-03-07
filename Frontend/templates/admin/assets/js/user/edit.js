// Get id from URL
console.log(document.location.href);
let url = new URL(document.location.href);
let id = url.searchParams.get("id");
console.log(id);

// Get token from localStorage
let token = localStorage.getItem("USER_TOKEN");

const loadRolesWithSelected = async (roleId) => {
  // Set option for role
  let roleTag = document.getElementById("roleId");
  let content = "";
  // Get roles from backend
  axios({
    url: "http://localhost:8080/api/admin/role",
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => {
      let roles = response.data;
      roles.forEach((role) => {
        let selected = roleId === role.id ? "selected" : "";
        content += `
            <option value=${role.id} ${selected}>${role.description}</option>
        `;
      });
      roleTag.innerHTML = content;
    })
    .catch((error) => console.log(error));
};

const loadData = () => {
  // Get user data
  axios({
    url: `http://localhost:8080/api/admin/human/${id}`,
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((response) => {
      console.log({ response });
      let user = response.data;
      console.log(user);
      // Set value to form data

      console.log(user.email);
      console.log(user.fullname);
      console.log(user.password);

      document.getElementById("email").value = user.email;
      document.getElementById("fullname").value = user.fullname;

      document.getElementById("avatar").value = user.avatar;
      document.getElementById("roleId").value = user.roleId;

      return user.roleId;
    })
    .then((roleId) => {
      loadRolesWithSelected(roleId);
    })
    .catch((error) => {
      console.log({ error });
    });
};

loadData();

const getInputForm = () => {
  // Get user infor from form
  // let password = document.getElementById("password").value;
  // let passwordConfirm = document.getElementById("passwordConfirm").value;
  // if (password !== passwordConfirm) {
  //   swal("Thất bại!", "Mật khẩu xác nhận không đúng", "error");
  //   return;
  // }

  let email = document.getElementById("email").value;
  let fullname = document.getElementById("fullname").value;
  let password = document.getElementById("password").value;
  let passwordConfirm = document.getElementById("passwordConfirm").value;
  let avatar = document.getElementById("avatar").value;
  let roleId = document.getElementById("roleId").value;

  // Validate data
  // Validate data
  if (fullname.length == 0) {
    document.getElementById("fullnameError").innerHTML = "Vui lòng nhập họ tên";
    return;
  } else {
    document.getElementById("fullnameError").innerHTML = "";
  }
  if (email.length == 0) {
    document.getElementById("emailError").innerHTML = "Vui lòng nhập email";
    return;
  } else if (
    /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(
      email
    ) === false
  ) {
    document.getElementById("emailError").innerHTML =
      "Email không đúng định dạng";
    return;
  } else {
    document.getElementById("emailError").innerHTML = "";
  }
  if (password.length > 0 && password.length < 6) {
    document.getElementById("passwordError").innerHTML =
      "Mật khẩu không nhỏ hơn 6 ký tự";
    return;
  }
  if (passwordConfirm !== password) {
    document.getElementById("confirmError").innerHTML =
      "Mật khẩu xác nhận không đứng";
    console.log("pass %s", password);
    console.log("confirm %s", passwordConfirm);
    return;
  } else {
    document.getElementById("confirmError").innerHTML = "";
  }

  // Create user object
  let user = {
    id: id,
    email: email,
    fullname: fullname,
    password: password,
    avatar: avatar,
    roleId: roleId,
  };

  console.log(user);

  // Call api update
  axios({
    url: `http://localhost:8080/api/admin/human/${id}`,
    method: "PUT",
    data: user,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((reponse) => {
      console.log({ reponse });
      swal("Thành Công", "Cập nhật thành công", "success").then(() => {
        document.location.href = "../../../../admin/user/index.html";
      });
    })
    .catch((error) => {
      console.log({ error });
      swal("Thất bại", "Thêm mới thất bại!", "error");
    });
};
