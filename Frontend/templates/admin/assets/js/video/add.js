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

const loadVideoDuration = () => {
  let videoInput = document.getElementById("videoFile");
  let videoElement = document.getElementById("videoPlay");

  videoElement.setAttribute("src", URL.createObjectURL(videoInput.files[0]));
  // Load video
  videoElement.load();

  // Process metadata
  videoElement.addEventListener("loadedmetadata", (e) => {
    console.log(videoElement.duration);
    // Calculate to display duration
    let duration = videoElement.duration;
    let minutes = parseInt(duration / 60, 10);
    let seconds = parseInt(duration % 60, 10);
    let displayDuration = minutes + ":" + seconds;
    // Load duration to duration input
    document.getElementById("duration").value = displayDuration;

    // upload video
    uploadVideo();
  });
};

const addVideo = () => {
  // Get information in form
  let title = document.getElementById("title").value;
  let courseId = document.getElementById("courseSelect").value;
  let duration = document.getElementById("duration").value;
  let url = document.getElementById("videoUrl").value;
  // change from duration to seconds
  let timeArray = duration.split(":");
  let timeCount = parseInt(timeArray[0]) * 60 + parseInt(timeArray[1]);
  // Create information object
  let videoModel = {
    courseId: courseId,
    timeCount: timeCount,
    title: title,
    url: url,
  };
  console.log(videoModel);
  axios({
    url: "http://localhost:8080/api/admin/video",
    method: "POST",
    data: videoModel,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log(resp);
      swal("Thông báo!", "Thêm mới thành công!", "success").then((value) => {
        window.location.href = "../../../../admin/video/video-list.html";
      });
    })
    .catch((error) => {
      console.log({ error });
      swal("Thông báo!", "Thêm mới thất bại!", "error");
    });
};

const uploadVideo = () => {
  let videoInput = document.getElementById("videoFile");
  // Add file to form data
  let formData = new FormData();
  formData.append("file", videoInput.files[0]);
  // Call file upload api
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
      console.log({ error });
    });
};
