
// function saveAvatar() {
//     let avatarInput = document.getElementById("avatar");
//     // kiểm tra đối tượng chèn hình chưa ( chưa)


//     // add file vào formdata
//     let formData = new FormData();
//     formData.append('file', avatarInput.files[0]);

//     axios({
//         url: 'http://localhost:8080/api/admin/file/profile',
//         method: 'POST',
//         data: formData,
//         headers: {
//             'Content-Type': 'multipart/form-data',
//             Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
//         }
//     })
//     .then(function(resp){
//         let image = resp.data;
//         // tới img
//         let imgAvatar = document.getElementById('imgAvatar');
//         // thay đổi src
//         imgAvatar.setAttribute('src', `http://localhost:8080/profile/${image}`);
//     })
//     .catch(function(err){
//         console.log(err)
//     })
    
// }

// function getFileName() {
//     var fullPath = document.getElementById('avatar').value;
//     if (fullPath) {
//         var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
//         var filename = fullPath.substring(startIndex);
//         if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
//             filename = filename.substring(1);
//         }
//         return filename;
//     }
// }


// function editProfileOther(){
        
        
//         axios({
//             url: 'http://localhost:8080/api/admin/user/current',
//             method: 'GET',
//             headers: {
//                 Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
//               }
    
//         })
//             .then(function (resp) {
//                 let userDto = resp.data;
//                 let idUser = userDto.id;
//                 let roleId = userDto.roleId;
//                 editOther(idUser);
                
    
//             })
//             .catch(function (err) {
//                 console.log(err);
              
//             })
         
         
    
// }
// function editProfileImage(){
        
        
//     axios({
//         url: 'http://localhost:8080/api/admin/user/current',
//         method: 'GET',
//         headers: {
//             Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
//           }

//     })
//         .then(function (resp) {
//             let userDto = resp.data;
//             let idUser = userDto.id;
//             let roleId = userDto.roleId;
//             editImage(idUser);
//             saveAvatar();
            

//         })
//         .catch(function (err) {
//             console.log(err);
          
//         })
     
     

// }

// function editProfilePassword(){
        
        
//     axios({
//         url: 'http://localhost:8080/api/admin/user/current',
//         method: 'GET',
//         headers: {
//             Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
//           }

//     })
//         .then(function (resp) {
//             let userDto = resp.data;
//             let idUser = userDto.id;
//             editPassword(idUser);

            

//         })
//         .catch(function (err) {
//             console.log(err);
          
//         })
     
     

// }




// function editOther(id){
//     var fullnameInput = document.getElementById('fullname').value;
//     var addressInput = document.getElementById('address').value;
//     var phoneInput = document.getElementById('phone').value;

//     var user = {
//         "id":id,
//         "fullname": fullnameInput,
//         "address":addressInput,
//         "phone":phoneInput
        
//     }

//     axios({
//         url: `http://localhost:8080/api/admin/user/profile`,
//         method: 'PUT',
//         data: user,
//         headers: {
//             Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
//           }
//     })
//         .then(function (resp) {
//             console.log('Thành công');
//             swal("Good job!", "Thêm Mới Thành Công!", "success");
        
//         })
//         .catch(function (err) {
//             console.log('lỗi xảy ra :' + console.error());
            
//         })




// }

// function editImage(id){
    
    

//     var user = {
//         "id":id,
//         "avatar":getFileName()
        
//     }

//     axios({
//         url: `http://localhost:8080/api/admin/user/profile`,
//         method: 'PUT',
//         data: user,
//         headers: {
//             Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
//           }
//     })
//         .then(function (resp) {
//             console.log('Thành công');
//             swal("Good job!", "Thêm Mới Thành Công!", "success");
        
//         })
//         .catch(function (err) {
//             console.log('lỗi xảy ra :' + console.error());
            
//         })

// }

// function editPassword(id){
    
//     var password = document.getElementById('password').value;

//     var user = {
//         "id":id,
//         "password":password
        
//     }

//     axios({
//         url: `http://localhost:8080/api/admin/user/profile`,
//         method: 'PUT',
//         data: user,
//         headers: {
//             Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
//           }
//     })
//         .then(function (resp) {
//             console.log('Thành công');
//             swal("Good job!", "Thêm Mới Thành Công!", "success");
        
//         })
//         .catch(function (err) {
//             console.log('lỗi xảy ra :' + console.error());
            
//         })

// }

// function showProfileName(){
    
//     axios({
//         url: 'http://localhost:8080/api/admin/user/current',
//         method: 'GET',
//         headers: {
//             Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
//           }

//     })
//         .then(function (resp) {
//             let userDto = resp.data;
//             let idUser = userDto.id;
//             let roleId = userDto.roleId;
//             getUserById(idUser);
            
            

//         })
//         .catch(function (err) {
//             console.log(err);
          
//         })
// }

// function getUserById(id){
//     axios({
//         url: `http://localhost:8080/api/admin/user/${id}`,
//         method: 'GET',
//         headers: {
//             Authorization: 'Bearer ' + localStorage.getItem("USER_TOKEN")
//           }

//     })
//         .then(function (resp) {
//             let userDto = resp.data;
            
//             document.getElementById('profileFullname').innerHTML = userDto.fullname;
//             document.getElementById('profileEmail').innerHTML = userDto.email;
//             document.getElementById('emailProfile').value = userDto.email;
            
            
            

//         })
//         .catch(function (err) {
//             console.log(err);
          
//         })
// }

// showProfileName();

// FIX 4/3

// function loadProfile(){

//     let token = localStorage.getItem("USER_TOKEN");
    
//     axios({
  
//         url:`http://localhost:8080/api/admin/user/${id}`,
//         method:"GET",
//         headers: {
//             Authorization: `Bearer ${token}`,
//           },
  
//     })
//     .then((response) => {
      
//         console.log(response)

       
  
//             document.getElementById("Email").value = userModel.email;
//             document.getElementById("FullName").value = userModel.name;
//             document.getElementById("Address").value = userModel.address;
//             document.getElementById("Phone").value = userModel.phone;
  
//     })
//     .catch((err) =>{
//         console.log(err);
//     })
  
  
  
//   }
//   loadProfile();

// let userProfile = JSON.parse(localStorage.getItem("USER_INFO"));

// let token = localStorage.getItem("USER_TOKEN");
// const loadProfile = () => {
//     // Get user data
 
//         console.log(userProfile);
  
//         let profile = document.getElementById("profile");

//         let content = `

//         <div class="tab-pane container-fluid active" id="profile">
//         <div class="row">
//           <div class="col-md-6">
//             <div class="form-group">
//               <label>Email</label>
//               <input id="Email" type="text" class="form-control" /> ${userProfile.name}
//             </div>
//             <div class="form-group">
//               <label>FullName</label>
//               <input id="FullName" type="text" class="form-control" />
//             </div>
//             <div class="form-group">
//               <label>Address</label>
//               <input id="Address" type="text" class="form-control" />
//             </div>
//           </div>
//           <div class="col-md-6">
//             <div class="form-group">
//               <label>Phone</label>
//               <input id="Phone" type="text" class="form-control" />
//             </div>
//             <div class="form-group">
//               <label>Website</label>
//               <div class="input-group">
//                 <div class="input-group-prepend">
//                   <span class="input-group-text">Website</span>
//                 </div>
//                 <input type="text" class="form-control" />
//               </div>
//             </div>
//             <div class="form-group">
//               <label>Facebook</label>
//               <div class="input-group">
//                 <div class="input-group-prepend">
//                   <span class="input-group-text"
//                     >http://www.facebook.com/</span
//                   >
//                 </div>
//                 <input type="text" class="form-control" />
//               </div>
//             </div>
//           </div>
//           <div class="col-md-12">
//             <button class="btn btn-outline-secondary">Save</button>
//           </div>
//         </div>
//       </div> `

//       content += `</div>`;
//       profile.innerHTML = content;
      
//   };
  
//  loadProfile();