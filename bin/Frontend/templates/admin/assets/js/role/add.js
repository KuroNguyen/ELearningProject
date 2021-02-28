const addRole = () => {
  // Get information in form
  let name = document.getElementById("name").value;
  let desc = document.getElementById("desc").value;
  // Create information object
  let role = {
    name: name,
    description: desc,
  };
  // Call api to create role
  // Get token from local storage
  let token = localStorage.getItem("USER_TOKEN");
  axios({
    url: "http://localhost:8080/api/admin/role",
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    data: role,
  })
    .then((resp) => {
      console.log(resp);
      swal("Thông báo!", "Thêm mới thành công!", "success").then((value) => {
        window.location.href = "../../../../admin/role/role-index.html";
      });
    })
    .catch((error) => {
      console.log(error);
      swal("Thông báo!", "Thêm mới thất bại!", "error");
    });
};
