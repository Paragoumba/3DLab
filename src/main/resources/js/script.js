const rotationElt = document.getElementById("rotation");

var scene = new THREE.Scene();
var camera = new THREE.PerspectiveCamera(60, window.innerWidth / window.innerHeight, 0.1, 1000);

var renderer = new THREE.WebGLRenderer({antialias: true});
renderer.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer.domElement);

/*var controls = new THREE.PointerLockControls(camera, document.body);
controls.movementSpeed = 1000;
controls.lookSpeed = 0.1;*/

window.addEventListener('resize', function(event){
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth, window.innerHeight);
});

const geometry = new THREE.BoxGeometry();
const material = new THREE.MeshPhongMaterial({color: 0xFFFFFF});
const skyBox = new THREE.Mesh(geometry, material);

skyBox.flipSided = false;
skyBox.position.x = 4.5;
skyBox.position.z = 4.5;
skyBox.scale.x = 20;
skyBox.scale.y = 20;
skyBox.scale.z = 20;

//scene.add(controls.getObject());
scene.add(skyBox);

scene.add(new THREE.AmbientLight(0x222222));

var light = new THREE.DirectionalLight();
light.position.set(4.5, 5, 4.5);
//light.target.set(5, 10, 5);
light.castShadow = true;
light.shadow.camera.zoom = 4; // tighter shadow map
scene.add(light);
//controls.lock();

camera.position.x = 4.5;
camera.position.y = 10;
camera.position.z = 4.5;

camera.rotation.x = -Math.PI / 2;

let lastFPS = 0;
let fps = 0;
let last = new Date().getTime();

function animate(){

    requestAnimationFrame(animate);
    //camera.rotation.x = Math.sin(new Date().getTime() / 1000) / 2;
    //camera.rotation.y = Math.sin(new Date().getTime() / 1000) / 2;
    //camera.rotation.z = Math.sin(new Date().getTime() / 1000) / 2;
    let now = new Date().getTime();

    if (now - last > 1000){

        last = now;
        lastFPS = fps;
        fps = 0;

    }

    rotationElt.textContent = "x:" + 180 / Math.PI * camera.rotation.x + " y:" + 180 / Math.PI * camera.rotation.y + " z:" + 180 / Math.PI * camera.rotation.z + " FPS:" + lastFPS;
    renderer.render(scene, camera);
    ++fps;

}

let levels;

importData();
animate();

function importData(){

    fetch("data.json")
        .then(data => data.json())
        .then(data => importLab(data.levels))
        .catch(console.log);

}

function importLab(data){

    levels = [];

    let i = 0;
    for (const level of data){

        const lab = level.lab;
        const newLevel = levels[i] = {
            lab: {
                width: lab.width,
                height: lab.height,
                rows: []
            }
        };

        for (let r = 0; r < newLevel.lab.width; ++r){

            newLevel.lab.rows[r] = [];

        }

        for (const block of lab.walls){

            const geometry = new THREE.BoxGeometry();
            const material = new THREE.MeshPhongMaterial({color: 0xFFFFFF});
            const cube = new THREE.Mesh(geometry, material);

            cube.position.x = block.x;
            cube.position.z = block.y;

            newLevel.lab.rows[block.x][block.y] = cube;

        }

        for (let x = 0; x < newLevel.lab.width; ++x){
            for (let y = 0; y < newLevel.lab.height; ++y){

                let object = newLevel.lab.rows[x][y];

                if (object === undefined){

                    const geometry = new THREE.PlaneGeometry();
                    const material = new THREE.MeshPhongMaterial({color: 0xFF0000});
                    object = new THREE.Mesh(geometry, material);

                    object.position.x = x;
                    object.position.z = y;
                    object.rotation.x = -Math.PI / 2;
                    newLevel.lab.rows[x][y] = object;

                }

                scene.add(object);

            }
        }

        ++i;

    }
}