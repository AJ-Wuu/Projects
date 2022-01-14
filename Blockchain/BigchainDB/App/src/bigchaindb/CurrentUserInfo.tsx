const currentUserInfo = {
    name: "",
    email: "",
    institution: [""],
    account: ""
}

export const setCurrentUserInfo = (name:string, email:string, institution:string[], account:any) => {
    currentUserInfo.name = name;
    currentUserInfo.email = email;
    currentUserInfo.institution = institution;
    currentUserInfo.account = account;

    localStorage.setItem("currentuserinfo", JSON.stringify(currentUserInfo));
    console.log("file created");
}