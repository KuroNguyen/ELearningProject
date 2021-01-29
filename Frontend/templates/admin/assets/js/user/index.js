let tBody = document.getElementById("tbodyUser");

const loadData = () => {
  let content = "";
  axios({
    url: "http://localhost:8080/api/admin/user",
    method: "GET",
  })
    .then(function (response) {
      let users = response.data;
      users.forEach((user) => {
        content += `
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.fullname}</td>
                        <td>${user.email}</td>
                        <td>${user.avatar}</td>
                        <td>${user.roleDesc}</td>
                        <td class="w-25">
                            <a href="edit.html?id=${user.id}" class="btn btn-sm btn-info">Sửa</a>
                            <a href="javascript:void(0)" onclick="deleteUser(${user.id})" class="btn btn-sm btn-danger">Xóa</a>
                        </td>
                    </tr>
                `;
      });
      tBody.innerHTML = content;
    })
    .catch(function (error) {
      console.log(error);
    });
};

loadData();

const deleteUser = (id) => {
  swal("Bạn có chắc chắn muốn xóa không?", { buttons: true }).then((value) => {
    if (value === true) {
      axios({
        url: `http://localhost:8080/api/admin/user/${id}`,
        method: "DELETE",
      })
        .then((resp) => {
          swal("Thành công!", "Xóa thành công!", "success").then(function () {
            loadData();
          });
        })
        .catch((error) => {
          console.log(error);
          swal("Thất bại!", "Xóa thất bại!", "error");
        });
    }
  });
};
