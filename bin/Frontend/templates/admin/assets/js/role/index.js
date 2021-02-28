const loadRoles = () => {
  // Reference roleList in DOM
  let tbodyRole = document.getElementById("tbodyRole");
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  // Retrieve roles by calling api
  axios({
    url: "http://localhost:8080/api/admin/role",
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log(resp);
      // Create content
      let content = "";
      // Get role array from response
      let roleArray = resp.data;
      let i = 1; // index variable
      for (const role of roleArray) {
        content += `<tr>
        <td>${i}</td>
        <td>${role.name}</td>
        <td>${role.description}</td>
        <td>
            <a 
                class="btn btn-sm btn-info btn-round py-1 font-weight-bold"
                href="role-edit.html?id=${role.id}">Sửa</a>
            <a 
                class="btn btn-sm btn-danger btn-round py-1 font-weight-bold"
                href="javascript:void(0)"
                onclick="deleteRole(${role.id})">Xóa</a>
        </td>
        </tr>`;
        i++;
      }
      tbodyRole.innerHTML = content;
    })
    .catch((error) => {
      console.log(error);
    });
};
loadRoles();

const deleteRole = (id) => {
  // Show alert to confirm deletion action
  swal("Bạn có muốn xóa hay không?", { button: true }).then((value) => {
    if (value === true) {
      // Get token from localStorage
      let token = localStorage.getItem("USER_TOKEN");
      // Call deleteRole api
      axios({
        url: `http://localhost:8080/api/admin/role/${id}`,
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
        .then((resp) => {
          swal("Thành công", "Xóa thành công!", "success").then(() => {
            loadRoles();
          });
        })
        .catch((error) => {
          console.log(error);
          swal("Thất bại", "Xóa thất bại!", "error");
        });
    }
  });
};
