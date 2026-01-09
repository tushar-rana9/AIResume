const form = document.getElementById("uploadForm");
const fileInput = document.getElementById("file");
const fileName = document.getElementById("fileName");
const uploadStatus = document.getElementById("uploadStatus");
const loading = document.getElementById("loading");
const result = document.getElementById("result");

/* 🔹 File select hone par name show kare */
fileInput.addEventListener("change", () => {
    if (fileInput.files.length > 0) {
        fileName.innerText = "📄 Selected File: " + fileInput.files[0].name;
        uploadStatus.innerText = "";
    }
});

/* 🔹 Form submit */
form.addEventListener("submit", async (e) => {
    e.preventDefault();

    if (fileInput.files.length === 0) {
        uploadStatus.innerText = "❌ Please select a file first";
        uploadStatus.style.color = "red";
        return;
    }

    const formData = new FormData();
    formData.append("file", fileInput.files[0]);

    loading.classList.remove("hidden");
    result.classList.add("hidden");

    uploadStatus.innerText = "⏳ Uploading & analyzing resume...";
    uploadStatus.style.color = "blue";

    try {
        const res = await fetch("/api/uploadResume", {
            method: "POST",
            body: formData
        });

        if (!res.ok) throw new Error("Upload failed");

        const data = await res.json();

        document.getElementById("summary").innerText = data.aiSummary;
        document.getElementById("ats").innerText = data.atsReport;
        document.getElementById("bias").innerText = data.biasReport;
        document.getElementById("skills").innerText = data.extractedSkills.join(", ");
        document.getElementById("match").innerText = data.jobMatchPercentage;

        uploadStatus.innerText = "✅ Resume uploaded & analyzed successfully!";
        uploadStatus.style.color = "green";

        result.classList.remove("hidden");

    } catch (err) {
        uploadStatus.innerText = "❌ Error while analyzing resume";
        uploadStatus.style.color = "red";
        console.error(err);
    } finally {
        loading.classList.add("hidden");
    }
});
