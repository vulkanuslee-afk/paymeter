# 라이선스 선택 가이드

앱을 유료로 판매할 계획이 있다면 라이선스 선택이 중요합니다. 이 파일은 결정을 돕기 위한 정리이며, 저장소에 남겨두거나 결정 후 삭제하셔도 됩니다.

## 핵심 원칙

**공개 저장소 + 오픈소스 라이선스 = 누구나 빌드해서 판매 가능**

MIT나 Apache 같은 라이선스를 붙이면 제3자가 이 코드를 그대로 빌드해 자기 이름으로 스토어에 올리는 것을 막을 수 없습니다. 무료판 광고 수익과 유료판 판매를 계획하고 있다면 이 점을 반드시 고려하세요.

---

## 선택지 1. 비공개 저장소 (판매에 가장 안전)

GitHub 무료 계정으로도 비공개 저장소를 만들 수 있습니다.

- 코드 백업과 버전 관리라는 목적은 그대로 달성
- 소스가 공개되지 않아 복제 위험 없음
- 나중에 언제든 공개로 전환 가능 (반대는 어려움)

**단점:** 포트폴리오로 보여주거나 다른 개발자의 기여를 받을 수 없습니다.

> 코드 관리가 주목적이라면 이 방식을 권합니다.

---

## 선택지 2. 공개 + 저작권 보유 (현재 기본 설정)

지금 `LICENSE` 파일에 적용된 방식입니다.

- 소스는 공개하되 재배포·상업적 이용은 금지
- 포트폴리오로 활용 가능
- "소스 공개형 상용 소프트웨어"에 해당하며, 오픈소스는 아닙니다

**단점:** 법적 구속력은 있지만, 실제로 누군가 무단 사용할 경우 대응은 본인 몫입니다. 또한 GitHub에서 오픈소스로 인정되지 않아 일부 배지·기능을 쓸 수 없습니다.

---

## 선택지 3. 완전 오픈소스 (MIT 등)

수익화를 포기하거나, 코드 공개 자체를 목적으로 할 때 선택합니다.

```
MIT License

Copyright (c) 2026 (이름)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

이 내용을 쓰려면 위 텍스트를 `LICENSE` 파일에 덮어쓰세요.

---

## 추천 정리

| 목적 | 추천 |
|---|---|
| 판매가 주목적, 코드 백업만 필요 | **비공개 저장소** |
| 판매도 하고 포트폴리오도 원함 | **공개 + 저작권 보유** (현재 설정) |
| 수익화 계획 없음, 공유가 목적 | **MIT** |

---

## 자료 저작권 별도 확인 사항

코드와 별개로, 앱에 포함된 자료들의 권리를 확인해 두세요.

| 자료 | 상태 |
|---|---|
| DSEG7 폰트 | SIL OFL — 상업적 사용·재배포 허용 (출처 표기 필요, NOTICE.md에 기재됨) |
| 앱 아이콘 | 직접 생성한 이미지 |
| 말 애니메이션 | 직접 만든 영상에서 추출 |
| 효과음 (현금 소리, 북소리) | **출처와 사용 조건을 직접 확인하세요.** 무료 배포 사이트에서 받은 것이라도 상업적 이용 가능 여부와 출처 표기 의무가 다를 수 있습니다. 불확실하다면 상업적 이용이 명시적으로 허용된 음원으로 교체하는 편이 안전합니다. |
| 영수증 문구 300개 | 직접 작성한 문구와 한국 속담 위주로 구성 |
