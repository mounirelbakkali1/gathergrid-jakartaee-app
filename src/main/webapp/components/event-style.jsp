<style>
    .events-body{
        margin-left:13vw;
        margin-right:13vw;

    }
    .blog-card {
        max-width: 80%;
        box-sizing: border-box;
        margin: 2rem auto;
        box-shadow: 1px 3px 4px rgba(0, 0, 0, 0.2);
        overflow: hidden;
        display: flex;
        flex-direction: column;
        transition: transform 0.5s ease-in-out;
        color: unset;
        text-decoration: unset;
    }

    .blog-card:hover {
        transform: scale(1.03);
    }

    .blog-card img {
        height: 10rem;
        width: 100%;
        object-fit: cover;
        border-radius: 6px 6px 0 0;
    }

    .blog-content {
        background-color: rgb(245, 250, 250);
        padding: 0.3rem 2rem 1rem 2rem;
    }

    .author {
        display: flex;
        align-items: center;
        margin-top: 1rem;
        overflow: hidden;
    }

    .author img {
        width: 4rem;
        height: 4rem;
        border-radius: 50%;
        object-fit: cover;
        margin-right: 5px;
        border: 1px solid rgb(164, 160, 160);
    }

    .author p {
        font-style: italic;
    }

    .blog-label {
        display: inline-block;
        background-color: rgb(194, 131, 16);
        color: white;
        border-radius: 15px;
        padding: 0 0.8rem;
    }
    .blog-label-sucess {
         display: inline-block;
         background-color: rgb(9, 160, 1);
         color: white;
         border-radius: 15px;
        padding: 0 0.8rem;
        font-size: 12px;
     }
    #badge {
        position: absolute;
        background-color: #8a7575;
        color: white;
        padding: 5px;
        transform: rotate(45deg);
        top: 21px;
        right: 2px;
    }


    @media (min-width: 48rem) {
        .blog-card {
            max-width: 30%;
        }
    }
</style>