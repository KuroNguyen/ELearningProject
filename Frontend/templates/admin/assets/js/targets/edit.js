// Get id from URL
let url = new URL(document.location.href);
let id = url.searchParams.get("id");

const loadData = () => {
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  // Call api to get courses first
  axios({
    url: `http://localhost:8080/api/admin/course`,
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log(resp);
      let content = ``;
      // Set values for course select
      for (const course of resp.data) {
        content += `<option value="${course.id}">${course.title}</option>`;
      }
      courseSelect.innerHTML = content;
      // Call api to get target data
      axios({
        url: `http://localhost:8080/api/admin/target/${id}`,
        method: "GET",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }).then((resp) => {
        let target = resp.data;
        // Set value for UI element
        document.getElementById("title").value = target.title;
        document.getElementById("courseSelect").value = target.courseId;
      });
    })
    .catch((error) => {
      console.log({ error });
    });
};
loadData();

const updateTarget = () => {
  // Get information in form
  let title = document.getElementById("title").value;
  let courseId = document.getElementById("courseSelect").value;
  // Create information object
  let target = {
    id: id,
    title: title,
    courseId: courseId,
  };
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  // Call api to get role
  axios({
    url: `http://localhost:8080/api/admin/target/${id}`,
    method: "PUT",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    data: target,
  })
    .then((resp) => {
      console.log(resp);
      swal("Thông báo!", "Sửa đổi thành công", "success").then(() => {
        document.location.href = "../../../admin/targets/target-list.html";
      });
    })
    .catch((error) => {
      console.log(error);
      swal("Thông báo!", "Sửa đổi thất bại", "error");
    });
};
