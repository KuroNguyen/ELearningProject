// Get id from URL
let url = new URL(document.location.href);
let id = url.searchParams.get("id");

const loadData = () => {
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  // Call api to get role
  axios({
    url: `http://localhost:8080/api/admin/role/${id}`,
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log(resp);
      let role = resp.data;
      // Set value for UI element
      document.getElementById("name").value = role.name;
      document.getElementById("desc").value = role.description;
    })
    .catch((error) => {
      console.log(error);
    });
};
loadData();

const updateRole = () => {
  // Get information in form
  let name = document.getElementById("name").value;
  let description = document.getElementById("desc").value;
  // Create updateRole model
  let roleModel = {
    id: id,
    name: name,
    description: description,
  };
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  // Call api to get role
  axios({
    url: `http://localhost:8080/api/admin/role/${id}`,
    method: "PUT",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    data: roleModel,
  })
    .then((resp) => {
      console.log(resp);
      swal("Thông báo!", "Sửa đổi thành công", "success").then(() => {
        document.location.href = "../../../admin/role/role-index.html";
      });
    })
    .catch((error) => {
      console.log(error);
      swal("Thông báo!", "Sửa đổi thất bại", "error");
    });
};
