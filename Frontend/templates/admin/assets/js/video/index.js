const loadVideos = () => {
  // Reference videoList in DOM
  let tbodyVideo = document.getElementById("tbodyVideo");
  // Get token from localStorage
  let token = localStorage.getItem("USER_TOKEN");
  // Retrieve videos by calling api
  axios({
    url: "http://localhost:8080/api/admin/video",
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log(resp);
      // Create content
      let content = "";
      // Get video array from response
      let videoArray = resp.data;
      let i = 1; // index variable
      for (const video of videoArray) {
        // Process timeCount to display duration
        let minutes = parseInt(video.timeCount / 60, 10);
        let seconds = parseInt(video.timeCount % 60, 10);
        let duration = minutes + ":" + seconds;
        content += `<tr>
          <td>${i}</td>
          <td>${video.title}</td>
          <td>${video.url}</td>
          <td>${duration}</td>
          <td>${video.courseTitle}</td>
          <td>
              <a 
                  class="btn btn-sm btn-info btn-round py-1 font-weight-bold"
                  href="video-edit.html?id=${video.id}">Sửa</a>
              <a 
                  class="btn btn-sm btn-danger btn-round py-1 font-weight-bold"
                  href="javascript:void(0)"
                  onclick="deleteVideo(${video.id})">Xóa</a>
          </td>
          </tr>`;
        i++;
      }
      tbodyVideo.innerHTML = content;
    })
    .catch((error) => {
      console.log(error);
    });
};
loadVideos();

const deleteVideo = (id) => {
  // Show alert to confirm deletion action
  swal("Bạn có muốn xóa hay không?", { button: true }).then((value) => {
    if (value === true) {
      // Get token from localStorage
      let token = localStorage.getItem("USER_TOKEN");
      // Call deleteVideo api
      axios({
        url: `http://localhost:8080/api/admin/video/${id}`,
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
        .then((resp) => {
          swal("Thành công", "Xóa thành công!", "success").then(() => {
            loadVideos();
          });
        })
        .catch((error) => {
          console.log(error);
          swal("Thất bại", "Xóa thất bại!", "error");
        });
    }
  });
};
