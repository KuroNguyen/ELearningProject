// Get id form url
console.log(document.location.href);
let url = new URL(document.location.href);
let id = url.searchParams.get("id");
console.log(id);

// Get token from localStorage
let token = localStorage.getItem("USER_TOKEN");

const loadData = () => {
  // Call api to get courses first
  axios({
    url: `http://localhost:8080/api/admin/course/${id}`,
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

loadData();
