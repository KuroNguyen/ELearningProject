// Get id form url
console.log(document.location.href);
let url = new URL(document.location.href);
let id = url.searchParams.get("id");
console.log(id);

// Get token from localStorage
let token = localStorage.getItem("USER_TOKEN");

// Render video function
const loadVideo = (videoArray) => {
  console.log(videoArray);
  let content = "";

  for (const video of videoArray) {
    content += `<li>
    <a href="#" class="btn-video" data-video-id="6xB-uXqbOqo">
      <span>
        <i class="fa fa-play-circle mr-1"></i>
        ${video.title}
      </span>
      <span>5:32</span>
    </a>
  </li>`;
  }
  document.getElementById("list-content").innerHTML = content;
};

const loadData = () => {
  // Call api to get course data
  axios({
    url: `http://localhost:8080/api/course/${id}`,
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  })
    .then((resp) => {
      console.log({ resp });
      let videoArray = resp.data.videos;
      loadVideo(videoArray);
    })
    .catch((error) => {
      console.log({ error });
    });
};

const loadCourseTargets = () => {
  // Call api to get targets
  axios({
    url: `http://localhost:8080/api/course/${id}`,
    method: "GET",
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
};
loadData();
