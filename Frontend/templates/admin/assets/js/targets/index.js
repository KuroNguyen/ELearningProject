const loadTargets = () => {
  // Reference targetList in DOM
  let tbodyTarget = document.getElementById("tbodyTarget");
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  // Retrieve targets by calling api
  axios({
    url: "http://localhost:8080/api/admin/target",
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log(resp);
      // Create content
      let content = "";
      // Get target array from response
      let targetArray = resp.data;
      let i = 1; // index variable
      for (const target of targetArray) {
        content += `<tr>
      <td>${i}</td>
      <td>${target.title}</td>
      <td>${target.courseTitle}</td>
      <td>
          <a 
              class="btn btn-sm btn-info btn-round py-1 font-weight-bold"
              href="target-edit.html?id=${target.id}">Sửa</a>
          <a 
              class="btn btn-sm btn-danger btn-round py-1 font-weight-bold"
              href="javascript:void(0)"
              onclick="deleteTarget(${target.id})">Xóa</a>
      </td>
      </tr>`;
        i++;
      }
      tbodyTarget.innerHTML = content;
    })
    .catch((error) => {
      console.log(error);
    });
};
loadTargets();

const deleteTarget = (id) => {
  // Show alert to confirm deletion action
  swal("Bạn có muốn xóa hay không?", { button: true }).then((value) => {
    if (value === true) {
      // Get token from localStorage
      let token = localStorage.getItem("USER_TOKEN");
      // Call deleteTarget api
      axios({
        url: `http://localhost:8080/api/admin/target/${id}`,
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
        .then((resp) => {
          swal("Thành công", "Xóa thành công!", "success").then(() => {
            loadTargets();
          });
        })
        .catch((error) => {
          console.log(error);
          swal("Thất bại", "Xóa thất bại!", "error");
        });
    }
  });
};
