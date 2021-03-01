// Get id from URL
console.log(document.location.href);
let url = new URL(document.location.href);
let id = url.searchParams.get("id");
console.log(id);
// Get token from localStorage
let token = localStorage.getItem("USER_TOKEN");

const loadData = () => {
  // Call api to get courses first
  axios({
    url: `http://localhost:8080/api/admin/course`,
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  }).then((resp) => {
    console.log(resp);
    let content = ``;
    // Set values for course select
    for (const course of resp.data) {
      content += `<option value="${course.id}">${course.title}</option>`;
    }
    courseSelect.innerHTML = content;

    // Get video data
    axios({
      url: `http://localhost:8080/api/admin/video/${id}`,
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        let video = response.data;
        console.log(video);
        // Set value to form data
        document.getElementById("title").value = video.title;
        document.getElementById("courseSelect").value = video.courseId;
        document.getElementById("videoUrl").value = video.url;
        // Calculate to display duration
        let duration = video.timeCount;
        let minutes = parseInt(duration / 60, 10);
        let seconds = parseInt(duration % 60, 10);
        let displayDuration = minutes + ":" + seconds;
        // Load duration to duration input
        document.getElementById("duration").value = displayDuration;
      })
      .catch((error) => {
        console.log({ error });
      });
  });
};
loadData();

const updateVideo = () => {
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
    id: id,
    courseId: courseId,
    timeCount: timeCount,
    title: title,
    url: url,
  };
  console.log(videoModel);
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  // Call api to get role
  axios({
    url: `http://localhost:8080/api/admin/video/${id}`,
    method: "PUT",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    data: videoModel,
  })
    .then((resp) => {
      console.log(resp);
      swal("Thông báo!", "Sửa đổi thành công", "success").then(() => {
        document.location.href = "../../../admin/video/video-list.html";
      });
    })
    .catch((error) => {
      console.log({ error });
      swal("Thông báo!", "Sửa đổi thất bại", "error");
    });
};

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
      console.log("TESTTTTTTTTTTTTTTTTTTTTT: " + videoUrl);
      document.getElementById("videoUrl").value = videoUrl;
    })
    .catch((error) => {
      console.log({ error });
    });
};
