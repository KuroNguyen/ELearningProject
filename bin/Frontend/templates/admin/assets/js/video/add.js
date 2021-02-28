// Get token from localStorage
let token = localStorage.getItem("USER_TOKEN");

const loadCourse = () => {
  // Reference course selection in DOM
  let courseSelect = document.getElementById("courseSelect");
  // Get courses by calling api
  axios({
    url: "http://localhost:8080/api/admin/course",
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
    })
    .catch((error) => {
      console.log({ error });
    });
};
loadCourse();

const uploadVideo = () => {
  let videoInput = document.getElementById("videoFile");
  // Add file to form data
  let formData = new FormData();
  formData.append("file", videoInput.files[0]);

  axios({
    url: "http://localhost:8080/api/admin/file/upload",
    method: "POST",
    data: formData,
    headers: {
      "Content-Type": "multipart/form-data",
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      let videoUrl = resp.data;
      document.getElementById("videoUrl").value = videoUrl;
    })
    .catch((error) => {
      console.log(error);
    });
};

const addVideo = () => {
  // Get information in form
  let title = document.getElementById("title").value;
  let courseId = document.getElementById("courseSelect").value;
  // Create information object
  let target = {
    title: title,
    courseId: courseId,
  };
  // Call api to create role
  // Get token from local storage
  let token = localStorage.getItem("USER_TOKEN");
  axios({
    url: "http://localhost:8080/api/admin/target",
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    data: target,
  })
    .then((resp) => {
      console.log(resp);
      swal("Thông báo!", "Thêm mới thành công!", "success").then((value) => {
        window.location.href = "../../../../admin/target/target-list.html";
      });
    })
    .catch((error) => {
      console.log(error);
      swal("Thông báo!", "Thêm mới thất bại!", "error");
    });
};
