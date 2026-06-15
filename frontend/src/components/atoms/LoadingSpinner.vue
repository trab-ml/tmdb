<template>
    <div class="spinner-box">
        <span></span>
        <span></span>
        <span></span>
    </div>
</template>

<script setup lang="ts"></script>

<style lang="css" scoped>
.spinner-box {
    display: flex;
    justify-content: center;
    align-items: center;
    translate: 0 50%;
}

span {
    opacity: 0;
    border: 4rem solid #000;
    border-radius: 4rem;
    margin: 0 0.25rem;
}
.spinner-box span:first-child {
    animation: pulse 800ms ease-in-out 0s infinite;
    border-color: #00047b;
}
.spinner-box span:nth-child(2) {
    animation: pulse 800ms ease-in-out 1s infinite;
    border-color: #b90101;
}
.spinner-box span:last-child {
    animation: pulse 800ms ease-in-out 2s infinite;
    border-color: #88c200;
}

@keyframes pulse {
    50% { opacity: 0.5; }
    75% { opacity: 1; }
    100% { opacity: 0; animation: 0s;}
}
</style>
