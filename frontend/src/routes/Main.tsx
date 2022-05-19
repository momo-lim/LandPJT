import { useEffect, useRef } from "react";
import { Link } from "react-router-dom";
import "./Main.css";

export default function Main() {
  const sample = useRef<HTMLDivElement[]>([]);

  // 새로고침시 최상단으로 이동
  useEffect(() => {
    window.onload = () => {
      setTimeout(() => {
        window.scrollTo(0, 0);
      }, 100);
    };
  }, []);

  useEffect(() => {
    const option = {
      root: null,
      rootMargin: "-0px",
      threshold: 0.05,
    };
    const scrollObserver = new IntersectionObserver((entries) => {
      entries.forEach((entry) => {
        if (entry.isIntersecting) {
          entry.target.classList.add("test_obj");
        } else {
          entry.target.classList.remove("test_obj");
        }
      });
    }, option);

    sample.current.forEach((el) => {
      scrollObserver.observe(el);
    });
  }, []);

  return (
    <div>
      <div className="main">
        <video autoPlay loop muted className="video">
          <source src="video/ssafylandVideo3.mp4" type="video/mp4" />
        </video>
        <div className="start test_obj">
          <h1>Welcome to SsafyLand</h1>
          <p>싸피만의 메타버스 행사장을 즐겨보세요</p>
          <Link to="world">
            <button>시작하기</button>
          </Link>
        </div>
      </div>
      <div className="contentStart">
        <div
          ref={(el) =>
            ((sample.current as HTMLDivElement[])[0] = el as HTMLDivElement)
          }
          className="paddingStyle"
        >
          <img
            className="logoImage"
            src={process.env.PUBLIC_URL + "./image/ssafylandlogo.PNG"}
            alt="error"
          />

          <h2>싸피의 비대면 행사를 메타버스로 구현한 싸피랜드!</h2>
          <h2>
            다양한 게임에 참여하여 현장감 있게 행사를 즐기고 추억을 쌓아보세요{" "}
          </h2>

          <img
            className="firstImage"
            src={process.env.PUBLIC_URL + "./image/Photo.PNG"}
            alt="error"
          />
        </div>

        <h1
          ref={(el) =>
            ((sample.current as HTMLDivElement[])[1] = el as HTMLDivElement)
          }
        >
          컨텐츠 소개
        </h1>
        <div
          className="contentItem"
          ref={(el) =>
            ((sample.current as HTMLDivElement[])[2] = el as HTMLDivElement)
          }
        >
          <div className="contentText">
            <h1>무대</h1>
            <h2>싸피 행사를 진행하는 곳이에요</h2>
          </div>
          <img
            className="contentImage"
            src={process.env.PUBLIC_URL + "./image/Stage.PNG"}
            alt="error"
          />
        </div>
        <div
          className="contentItem"
          ref={(el) =>
            ((sample.current as HTMLDivElement[])[3] = el as HTMLDivElement)
          }
        >
          <img
            className="contentImage"
            src={process.env.PUBLIC_URL + "./image/Gold.PNG"}
            alt="error"
          />
          <div className="contentText">
            <h1>보물찾기</h1>
            <h2>풀숲에 숨어져 있는 보물을 찾아보세요</h2>
          </div>
        </div>
        <div
          className="contentItem"
          ref={(el) =>
            ((sample.current as HTMLDivElement[])[4] = el as HTMLDivElement)
          }
        >
          <div className="contentText">
            <h1>OX게임</h1>
            <h2>다른 교육생들과 같이 OX게임을 즐겨보세요</h2>
          </div>
          <img
            className="contentImage"
            src={process.env.PUBLIC_URL + "./image/OX.PNG"}
            alt="error"
          />
        </div>
        <div
          className="contentItem"
          ref={(el) =>
            ((sample.current as HTMLDivElement[])[5] = el as HTMLDivElement)
          }
        >
          <img
            className="contentImage"
            src={process.env.PUBLIC_URL + "./image/Photo.PNG"}
            alt="error"
          />
          <div className="contentText">
            <h1>포토존</h1>
            <h2>SsafyLand에서 추억을 남겨보세요</h2>
          </div>
        </div>
        <div
          className="contentItem"
          ref={(el) =>
            ((sample.current as HTMLDivElement[])[6] = el as HTMLDivElement)
          }
        >
          <div className="contentText">
            <h1>인내의 숲</h1>
            <h2>
              총 4단계로 이루어진 게임, 누가 먼저 올라가는지 기록을 세워보세요
            </h2>
          </div>
          <img
            className="contentImage"
            src={process.env.PUBLIC_URL + "./image/Forest.PNG"}
            alt="error"
          />
        </div>
        <div
          className="contentItem"
          ref={(el) =>
            ((sample.current as HTMLDivElement[])[7] = el as HTMLDivElement)
          }
        >
          <img
            className="contentImage"
            src={process.env.PUBLIC_URL + "./image/Gallery.PNG"}
            alt="error"
          />
          <div className="contentText">
            <h1>전시관</h1>
            <h2>지난 기수 우수 프로젝트들을 보실 수 있어요</h2>
          </div>
        </div>
      </div>
    </div>
  );
}
